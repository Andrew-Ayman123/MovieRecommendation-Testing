package org.example;

import java.util.*;

public class RecommendationEngine {

    // Maps each genre to a list of its movies.
    private Map<String, List<String>> genreMovieTitlesMap = new HashMap<>();
    // Keep a copy of all movies
    private List<Movie> movies;

    /**
     * Constructs a recommendation engine and processes the movie list into genres.
     *
     * @param movies List of movies.
    */
    public RecommendationEngine(List<Movie> movies) {
        this.movies = movies;

        // Use a set to remove duplicates
        Set<String> allGenres = new HashSet<>();

        // Get all movie genres
        for (Movie movie: movies) {
            allGenres.addAll(movie.genres());
        }

        // Build genreMovies map
        for (String genre: allGenres) {
            List<String> genreMovieTitles = new ArrayList<>();
            for (Movie movie: movies) {
                if (movie.genres().contains(genre)) {
                    genreMovieTitles.add(movie.title());
                }
            }
            genreMovieTitlesMap.put(genre, genreMovieTitles);
        }
    }

    /** Recommends a list of movie titles for a user based on liked genres
     *
     * @param user User to recommend movies for
     * @return List of movie titles
     * */
    public List<String> recommend(User user) {
        // Use a set to remove duplicates
        Set<String> recommendations = new HashSet<>();
        Set<String> likedGenres = new HashSet<>();

        // Get all user's liked genres
        for (String movieId: user.likedMovieIds()) {
            Movie movie = movies.stream()
                    .filter(m -> Objects.equals(m.id(), movieId))
                    .findFirst()
                    .orElse(null);
            if (movie != null)
                likedGenres.addAll(movie.genres());
        }

        // Get all movies with the same genres
        for (String likedGenre: likedGenres) {
            recommendations.addAll(genreMovieTitlesMap.get(likedGenre));
        }

        return new ArrayList<>(recommendations);
    }
}
