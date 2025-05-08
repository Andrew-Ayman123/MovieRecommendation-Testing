package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MovieWhiteTest {

    @Test
    public void equals_sameAttributes() {
        Movie movie1 = new Movie("LM002", "Before Us", List.of("Romance", "Drama"));
        Movie movie2 = new Movie("LM002", "Before Us", List.of("Romance", "Drama"));
        assertEquals(movie1, movie2);
    }

    @Test
    public void notEqual_differentTitle() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Horror", "Thriller"));
        Movie movie2 = new Movie("LM002", "The Conjuring", List.of("Horror", "Thriller"));
        assertNotEquals(movie1, movie2);
    }

    @Test
    public void notEqual_differentID() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Horror", "Thriller"));
        Movie movie2 = new Movie("LM102", "Annabelle", List.of("Horror", "Thriller"));
        assertNotEquals(movie1, movie2);
    }

    @Test
    public void notEqual_differentGenres() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Thriller"));
        Movie movie2 = new Movie("LM102", "Annabelle", List.of("Horror", "Thriller"));
        assertNotEquals(movie1, movie2);
    }

    @Test
    public void equals_sameGenresDifferentOrder() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Thriller", "Horror"));
        Movie movie2 = new Movie("LM002", "Annabelle", List.of("Horror", "Thriller"));
        assertEquals(movie1, movie2);
    }

    @Test
    public void toString_correctFormat() {
        Movie movie = new Movie("LM002", "Annabelle", List.of("Thriller", "Horror"));
        String expected = "Movie{title='Annabelle', id='LM002', genres=[Thriller, Horror]}";
        assertEquals(expected, movie.toString());
    }

    @Test
    void testMovieSetAndGetTitle() {
        Movie movie = new Movie("TM123", "The Matrix", List.of("Action", "Sci-Fi"));
        movie.setTitle("Inception");
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    void testMovieSetAndGetId() {
        Movie movie = new Movie("TM123", "The Matrix", List.of("Action", "Sci-Fi"));
        movie.setId("IN456");
        assertEquals("IN456", movie.getId());
    }

    @Test
    void testMovieSetAndGetGenres() {
        Movie movie = new Movie("TM123", "The Matrix", List.of("Action", "Sci-Fi"));
        movie.setGenres(List.of("Thriller", "Drama"));
        assertEquals(List.of("Thriller", "Drama"), movie.getGenres());
    }
}