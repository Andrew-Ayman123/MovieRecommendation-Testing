package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private FileHandler fileHandler;
    private Validation validation;
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        validation = new Validation();
        fileHandler = new FileHandler(validation);
        tempDir = Files.createTempDirectory("testDir");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(tempDir)
                .sorted((a, b) -> b.compareTo(a))
                .map(Path::toFile)
                .forEach(file -> {
                    if (!file.delete()) {
                        System.err.println("Failed to delete file: " + file.getAbsolutePath());
                    }
                });
    }

    @Test
    void testValidMoviesFile() throws IOException {
        Path file = tempDir.resolve("movies.txt");
        Files.write(file, List.of(
                "The Matrix,TM123",
                "Action,Sci-Fi",
                "The Conjuring,TC789",
                "Horror"
        ));

        List<Movie> movies = fileHandler.readMovies(file.toString());

        assertEquals(2, movies.size());
        assertEquals("TM123", movies.get(0).getId());
        assertTrue(movies.get(0).getGenres().contains("Sci-Fi"));
    }

    @Test
    void testMalformedMoviesFile() throws IOException {
        Path file = tempDir.resolve("malformed_movies.txt");
        Files.write(file, List.of("OnlyTitle,ID123"));

        InputException exception = assertThrows(InputException.class, () -> fileHandler.readMovies(file.toString()));
        assertTrue(exception.getMessage().contains("ERROR: Movie Id"));
    }

    @Test
    void testValidUsersFile() throws IOException {
        Path file = tempDir.resolve("users.txt");
        Files.write(file, List.of(
                "Alice,12345678A",
                "TMX123,TCJ789",
                "Bob,98765432B",
                "INC456"
        ));

        List<User> users = fileHandler.readUsers(file.toString());

        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).getName());
        assertEquals("12345678A", users.get(0).getId());
        assertEquals(2, users.get(0).getLikedMovieIds().size());
    }

    @Test
    void testDuplicateUserIds() throws IOException {
        Path file = tempDir.resolve("duplicate_users.txt");
        Files.write(file, List.of(
                "Alice,12345678A",
                "TMX123,TCJ789",
                "Bob,12345678A",
                "INC456"
        ));

        InputException exception = assertThrows(InputException.class, () -> fileHandler.readUsers(file.toString()));
        assertTrue(exception.getMessage().contains("User ID 12345678A is not unique"));
    }
    @Test
    void testRecommendationsFile() throws IOException {
        // Prepare input files
        Path moviesFile = tempDir.resolve("movies.txt");
        Files.write(moviesFile, List.of(
                "The Matrix,TM123",
                "Action,Sci-Fi",
                "The Conjuring,TC789",
                "Horror"
        ));

        Path usersFile = tempDir.resolve("users.txt");
        Files.write(usersFile, List.of(
                "Alice,12345678A",
                "TM123",
                "Bob,98765432B",
                "TC789"
        ));

        // Read input files
        fileHandler.readMovies(moviesFile.toString());
        fileHandler.readUsers(usersFile.toString());

        // Generate recommendations file
        Path recommendationsFile = tempDir.resolve("recommendations.txt");
        fileHandler.generateRecommendations(recommendationsFile.toString());

        // Validate recommendations file content
        List<String> lines = Files.readAllLines(recommendationsFile);
        assertEquals(4, lines.size());
        assertEquals("12345678A,Alice", lines.get(0));
        assertEquals("The Matrix", lines.get(1));
        assertEquals("98765432B,Bob", lines.get(2));
        assertEquals("The Conjuring", lines.get(3));
    }
}