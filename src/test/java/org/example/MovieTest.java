package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieTest {
    @Test
    public void equals_sameAttributes() {
        Movie movie1 = new Movie("LM002", "Before Us", List.of("Romance", "Drama"));
        Movie movie2 = new Movie("LM002", "Before Us", List.of("Romance", "Drama"));
        assertEquals(movie1, movie2);
    }

    @Test
    public void Notequal_differentTitle() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Horror", "Thriller"));
        Movie movie2 = new Movie("LM002", "The Conjuring", List.of("Horror", "Thriller"));
        assertNotEquals(movie1, movie2);
    }

    @Test
    public void DifferentID() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Horror", "Thriller"));
        Movie movie2 = new Movie("LM102", "Annabelle", List.of("Horror", "Thriller"));
        assertNotEquals(movie1, movie2);
    }

    @Test
    public void   differentGenres() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Thriller"));
        Movie movie2 = new Movie("LM102", "Annabelle", List.of("Horror", "Thriller"));
        assertNotEquals(movie1, movie2);
    }

    @Test
    public void sameGenresDifferentOrder() {
        Movie movie1 = new Movie("LM002", "Annabelle", List.of("Thriller", "Horror"));
        Movie movie2 = new Movie("LM002", "Annabelle", List.of("Horror", "Thriller"));
        assertEquals(movie1 , movie2);
    }

    @Test
    public void NotNull() {
        Movie movie = new Movie("LM002", "Annabelle", List.of("Thriller", "Horror"));
        assertNotEquals(null, movie);
    }

    /*
    "Movie{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                '}'
     */
    /* we need to check if it returns this format */
    @Test
    public void check_Return_String(){
        Movie movie = new Movie("LM002", "Annabelle", List.of("Thriller", "Horror"));
        String exp = "Movie{title='Annabelle', id='LM002', genres=[Thriller, Horror]}";
        assertEquals(movie.toString(),exp);
    }




}
