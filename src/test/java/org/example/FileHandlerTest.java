package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private FileHandler fileHandler;
    private Path tempDir;
    private Validation mockValidation;

    @BeforeEach
    void setUp() throws IOException {
        mockValidation = mock(Validation.class);
        fileHandler = new FileHandler(mockValidation);
        tempDir = Files.createTempDirectory("testDir");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    void testReadMoviesValid() throws IOException {
        Path file = tempDir.resolve("movies.txt");
        Files.write(file, List.of(
                "The Matrix,TMX123",
                "Action,Sci-Fi",
                "The Conjuring,TCJ789",
                "Horror"
        ));

        List<Movie> movies = fileHandler.readMovies(file.toString());

        assertEquals(2, movies.size());
        assertEquals("TMX123", movies.get(0).id());
        assertTrue(movies.get(0).genres().contains("Sci-Fi"));
    }

    @Test
    void testReadMoviesEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty_movies.txt");
        Files.createFile(file);

        List<Movie> movies = fileHandler.readMovies(file.toString());

        assertTrue(movies.isEmpty());
    }

    @Test
    void testReadUsersValid() throws IOException {
        Path file = tempDir.resolve("users.txt");
        Files.write(file, List.of(
                "Alice,12345678A",
                "TMX123,TCJ789",
                "Bob,98765432B",
                "INC456"
        ));

        List<User> users = fileHandler.readUsers(file.toString());

        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).name());
        assertEquals("12345678A", users.get(0).id());
        assertEquals(2, users.get(0).likedMovieIds().size());
    }

    @Test
    void testReadUsersEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty_users.txt");
        Files.createFile(file);

        List<User> users = fileHandler.readUsers(file.toString());

        assertTrue(users.isEmpty());
    }

    @Test
    void testWriteRecommendationsValid() throws IOException {
        Path file = tempDir.resolve("recommendations.txt");
        List<Recommendation> recommendations = List.of(
                new Recommendation("12345678A", "Alice", List.of("The Matrix", "Inception")),
                new Recommendation("98765432B", "Bob", List.of("The Conjuring"))
        );

        fileHandler.writeOutput(file.toString(), recommendations, null);

        List<String> lines = Files.readAllLines(file);
        assertEquals("Alice,12345678A", lines.get(0));
        assertEquals("The Matrix,Inception", lines.get(1));
        assertEquals("Bob,98765432B", lines.get(2));
        assertEquals("The Conjuring", lines.get(3));
    }

    @Test
    void testWriteErrorMessageOnly() throws IOException {
        Path file = tempDir.resolve("recommendation_error.txt");
        String errorMsg = "ERROR: Movie Title XYZ is wrong";

        fileHandler.writeOutput(file.toString(), null, errorMsg);

        List<String> lines = Files.readAllLines(file);
        assertEquals(1, lines.size());
        assertEquals(errorMsg, lines.get(0));
    }

    @Test
    void testWriteOutputWithNulls() throws IOException {
        Path file = tempDir.resolve("empty_output.txt");

        fileHandler.writeOutput(file.toString(), null, null);

        // File should be created, but empty
        List<String> lines = Files.readAllLines(file);
        assertTrue(lines.isEmpty());
    }

    @Test
    void testReadMoviesMissingGenresLine() throws IOException {
        Path file = tempDir.resolve("bad_movies.txt");
        Files.write(file, List.of("OnlyTitle,ID123"));

        assertThrows(NullPointerException.class, () -> fileHandler.readMovies(file.toString()));
    }

    @Test
    void testReadUsersMissingLikedMoviesLine() throws IOException {
        Path file = tempDir.resolve("bad_users.txt");
        Files.write(file, List.of("User1,ID999"));

        assertThrows(NullPointerException.class, () -> fileHandler.readUsers(file.toString()));
    }

    @Test
    void testReadMoviesNonexistentFile() {
        assertThrows(IOException.class, () -> fileHandler.readMovies("nonexistent.txt"));
    }

    @Test
    void testReadUsersNonexistentFile() {
        assertThrows(IOException.class, () -> fileHandler.readUsers("nonexistent.txt"));
    }
}
