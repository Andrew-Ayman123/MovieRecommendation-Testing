package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecommendationEngineTest {

    private List<Movie> movieList;
    private RecommendationEngine engine;

    @BeforeEach
    void setUp() {
        movieList = List.of(
                new Movie("TMX123", "The Matrix", List.of("Action", "Sci-Fi")),
                new Movie("INC456", "Inception", List.of("Action", "Thriller")),
                new Movie("TCJ789", "The Conjuring", List.of("Horror")),
                new Movie("INT321", "Interstellar", List.of("Sci-Fi", "Drama")),
                new Movie("NBK654", "The Notebook", List.of("Romance", "Drama")),
                new Movie("AQP111", "A Quiet Place", List.of("Horror", "Thriller"))
        );
        engine = new RecommendationEngine(movieList);
    }

    @Test
    void testRecommendSingleGenre() {
        User user = new User("Alice", "12345678A", List.of("TCJ789")); // Likes Horror
        List<String> recommendations = engine.recommend(user);
        assertTrue(recommendations.contains("A Quiet Place"));
        assertTrue(recommendations.contains("The Conjuring"));
        assertEquals(2, recommendations.size());
    }

    @Test
    void testRecommendMultipleGenres() {
        User user = new User("87654321B", "Bob", List.of("TMX123", "NBK654")); // Likes Action, Sci-Fi, Drama
        List<String> recommendations = engine.recommend(user);
        assertTrue(recommendations.contains("The Matrix"));
        assertTrue(recommendations.contains("Inception"));
        assertTrue(recommendations.contains("Interstellar"));
        assertTrue(recommendations.contains("The Notebook"));
        assertEquals(4, recommendations.size());
    }

    @Test
    void testRecommendWithNoLikedMovies() {
        User user = new User("11122233C", "Charlie", Collections.emptyList());
        List<String> recommendations = engine.recommend(user);
        assertTrue(recommendations.isEmpty());
    }

    @Test
    void testNoDuplicateRecommendations() {
        // Both movies are in genre Sci-Fi
        User user = new User("12121212F", "Omar", List.of("TMX123", "INT321"));
        List<String> recommendations = engine.recommend(user);
        assertEquals(4, recommendations.size());
        assertEquals(new HashSet<>(recommendations).size(), recommendations.size()); // Ensure uniqueness
    }

    @Test
    void testRecommendAllMoviesForSharedGenre() {
        User user = new User("33344455G", "George", List.of("AQP111")); // Likes Horror, Thriller
        List<String> recommendations = engine.recommend(user);
        assertTrue(recommendations.contains("The Conjuring"));
        assertTrue(recommendations.contains("A Quiet Place"));
        assertTrue(recommendations.contains("Inception")); // Thriller
        assertEquals(3, recommendations.size());
    }

    @Test
    void testCaseWhereMovieHasMultipleGenres() {
        User user = new User("55566677H", "Hana", List.of("INT321")); // Likes Sci-Fi, Drama
        List<String> recommendations = engine.recommend(user);
        assertTrue(recommendations.contains("The Matrix"));
        assertTrue(recommendations.contains("Interstellar"));
        assertTrue(recommendations.contains("The Notebook"));
        assertEquals(3, recommendations.size());
    }

    @Test
    void testEmptyMovieListInitialization() {
        RecommendationEngine emptyEngine = new RecommendationEngine(Collections.emptyList());
        User user = new User("00011122I", "Isaac", List.of("ANY123"));
        List<String> recommendations = emptyEngine.recommend(user);
        assertTrue(recommendations.isEmpty());
    }

    @Test
    void testMovieWithNoGenreDoesNotBreakLogic() {
        List<Movie> withEmptyGenre = new ArrayList<>(movieList);
        withEmptyGenre.add(new Movie("Unknown Movie", "UKN000", Collections.emptyList()));
        RecommendationEngine testEngine = new RecommendationEngine(withEmptyGenre);

        User user = new User("Judy", "11223344J", List.of("UKN000"));
        List<String> recommendations = testEngine.recommend(user);
        assertTrue(recommendations.isEmpty());
    }
}
