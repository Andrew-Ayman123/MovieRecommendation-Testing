package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    private Validation validation;

    @BeforeEach
    void setUp() {
        validation = new Validation();
    }

    // USERNAME VALIDATION


    @Test
    void validUsername_shouldNotThrow() {
        assertDoesNotThrow(() -> validation.validateUsername("John Doe"));
    }

    @Test
    void usernameStartingWithSpace_shouldThrow() {
        InputException exception = assertThrows(InputException.class,
                () -> validation.validateUsername(" john"));
        assertTrue(exception.getMessage().contains("User Name"));
    }

    @Test
    void emptyUsername_shouldThrow() {
        InputException exception = assertThrows(InputException.class,
                () -> validation.validateUsername(""));
        assertTrue(exception.getMessage().contains("User Name"));
    }

    @Test
    void nullUsername_shouldThrow() {
        InputException exception = assertThrows(InputException.class,
                () -> validation.validateUsername(null));
        assertTrue(exception.getMessage().contains("User Name"));
    }

    // USER ID VALIDATION

    @Test
    void validUserId_shouldNotThrow() {
        assertDoesNotThrow(() -> validation.ValidUserID("12345678A"));
    }

    @Test
    void invalidUserIdLength_shouldThrow() {
        assertThrows(InputException.class, () -> validation.ValidUserID("1234"));
    }

    @Test
    void invalidUserIdFormat_shouldThrow() {
        assertThrows(InputException.class, () -> validation.ValidUserID("ABCDEFGHJ"));
    }

    // USER VALIDATION

    @Test
    void validUser_shouldNotThrow() {
        User user = new User("12345678A", "Alice", List.of("TG123"));
        assertDoesNotThrow(() -> validation.uservalidation(user));
    }

    @Test
    void duplicateUserId_shouldThrow() {
        User user1 = new User("12345678A", "Bob", List.of("TG123"));
        User user2 = new User("12345678A", "Alice", List.of("TG123"));
        validation.uservalidation(user1);
        assertThrows(InputException.class, () -> validation.uservalidation(user2));
    }

    // MOVIE TITLE VALIDATION

    @Test
    void validMovieTitle_shouldNotThrow() {
        assertDoesNotThrow(() -> validation.validateMovieTitle("The Matrix"));
    }

    @Test
    void movieTitleLowercaseStart_shouldThrow() {
        assertThrows(InputException.class, () -> validation.validateMovieTitle("the Matrix"));
    }

    @Test
    void emptyMovieTitle_shouldThrow() {
        assertThrows(InputException.class, () -> validation.validateMovieTitle(""));
    }

    @Test
    void nullMovieTitle_shouldThrow() {
        assertThrows(InputException.class, () -> validation.validateMovieTitle(null));
    }

    // MOVIE ID VALIDATION

    @Test
    void validMovieId_shouldNotThrow() {
        assertDoesNotThrow(() -> validation.validateMovieID("TM123", "The Matrix"));
    }

    @Test
    void invalidMovieIdMismatch_shouldThrow() {
        assertThrows(InputException.class, () -> validation.validateMovieID("AB123", "The Matrix"));
    }

    // GENRE VALIDATION

    @Test
    void validGenres_shouldNotThrow() {
        assertDoesNotThrow(() -> validation.movievalidation(new Movie("TM123", "The Matrix", List.of("Action", "Sci-Fi"))));
    }

    @Test
    void emptyGenres_shouldThrow() {
        assertThrows(InputException.class, () -> validation.movievalidation(new Movie("TM123", "The Matrix", List.of())));
    }

    @Test
    void nullGenres_shouldThrow() {
        assertThrows(InputException.class, () -> validation.movievalidation(new Movie("TM123", "The Matrix", List.of())));
    }

    // MOVIE ID UNIQUENESS TESTS

    @Test
    void duplicateLastThreeDigitsMovieId_shouldThrow() {
        Movie movie1 = new Movie("MOV123", "Movie One", List.of("Action"));
        Movie movie2 = new Movie("FIL123", "Film Two", List.of("Drama"));
        validation.movievalidation(movie1);
        assertThrows(InputException.class, () -> validation.movievalidation(movie2));
    }

    @Test
    void differentLastThreeDigitsMovieId_shouldNotThrow() {
        Movie movie1 = new Movie("MOV123", "Movie One", List.of("Action"));
        Movie movie2 = new Movie("FIL124", "Film Two", List.of("Drama"));
        assertDoesNotThrow(() -> {
            validation.movievalidation(movie1);
            validation.movievalidation(movie2);
        });
    }

    @Test
    void getLastThreeDigits_invalidId_shouldThrow() {
        assertThrows(InputException.class, () -> validation.movievalidation(new Movie("MO", "Movie", List.of("Comedy"))));
    }

}
