package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading from user and movie files and writing recommendation files
 */
public class FileHandler {
    Validation validation;

    public FileHandler(Validation validation) {
        this.validation = validation;
    }

    /**
     * Reads movie data from the specified file path.
     * Line 1: Movie Title,Movie Id
     * Line 2: Genre1,Genre2,...
     * @param filePath The path to the movies file (e.g., "movies.txt").
     * @return Movie list.
     * @throws IOException If an I/O error occurs reading the file.
     */
    public List<Movie> readMovies(String filePath) throws IOException {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String titleIdLine;
            while ((titleIdLine = reader.readLine()) != null) {
                String genresLine = reader.readLine();

                String[] titleIdParts = titleIdLine.split(",");
                String title = titleIdParts[0].trim();
                validation.validateMovieTitle(title);

                String id = titleIdParts[1].trim();

                String[] genreParts = genresLine.split(",");
                List<String> genres = new ArrayList<>();
                for(String genre : genreParts) {
                    genres.add(genre.trim());
                }
                Movie movie = new Movie(id, title, genres);
                validation.movievalidation(movie);
                movies.add(movie);

            }
        }
        return movies;
    }

    /**
     * Reads user data from the specified file path.
     * Line 1: Username,User Id
     * Line 2: LikedMovieId1,LikedMovieId2,...
     * @param filePath The path to the users file.
     * @return User list.
     * @throws IOException If an I/O error occurs reading the file.
     */
    public List<User> readUsers(String filePath) throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String nameIdLine;
            while ((nameIdLine = reader.readLine()) != null) {
                String likedMoviesLine = reader.readLine();

                String[] nameIdParts = nameIdLine.split(",");
                String name = nameIdParts[0].trim();
                validation.ValidUsername(name);

                String id = nameIdParts[1].trim();
                validation.ValidUserID(id);

                List<String> likedMovies = new ArrayList<>();
                String[] likedMovieParts = likedMoviesLine.split(",");
                for (String movieId : likedMovieParts) {
                    if (!movieId.trim().isEmpty()) {
                        likedMovies.add(movieId.trim());
                    }
                }

                User user = new User(id, name, likedMovies);
                validation.uservalidation(user);
                users.add(user);
            }
        }
        return users;
    }

    /**
     * Writes recommendations or error messages to the specified file path.
     * If recommendations:
     * Line 1: Username,User Id
     * Line 2: RecommendedMovieTitle1,RecommendedMovieTitle2,...
     * If error:
     * Single line with error message.
     * @param filePath        The path to the output file.
     * @param recommendations Recommendation list.
     * @param errorMessage    Error message string.
     * @throws IOException If an I/O error occurs writing the file.
     */
    public void writeOutput(String filePath, List<Recommendation> recommendations, String errorMessage) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            if (errorMessage != null) {
                writer.write(errorMessage);
                writer.newLine();
            } else if (recommendations != null) {
                for (Recommendation recommendation : recommendations) {
                    writer.write(recommendation.username() + "," + recommendation.userId());
                    writer.newLine();
                    writer.write(String.join(",", recommendation.recommendedMovieTitles()));
                    writer.newLine();
                }
            } else {
                // Case where both recommendations and error message are null - write empty file or log?
                System.err.println("Warning: No recommendations or error message provided for writing to " + filePath);
            }
        }
    }
}