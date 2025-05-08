package org.example;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationWhiteTest {

    private final Validation validation = new Validation();

    @Test
    public void testValidateUsername_Valid() {
        validation.validateUsername("John Doe");
    }

    @Test
    public void testValidateUsername_Invalid() {
        assertThrows(InputException.class, () -> validation.validateUsername(""));
        assertThrows(InputException.class, () -> validation.validateUsername(null));
        assertThrows(InputException.class, () -> validation.validateUsername("123Invalid"));
    }

    @Test
    public void testValidateMovieTitle_Valid() {
        validation.validateMovieTitle("The Matrix");
    }

    @Test
    public void testValidateMovieTitle_Invalid() {
        assertThrows(InputException.class, () -> validation.validateMovieTitle(""));
        assertThrows(InputException.class, () -> validation.validateMovieTitle("the matrix"));
    }

    @Test
    public void testValidateMovieID_Valid() {
        validation.validateMovieID("TM123", "The Matrix");
    }

    @Test
    public void testValidateMovieID_Invalid() {
        assertThrows(InputException.class, () -> validation.validateMovieID("123", "The Matrix"));
        assertThrows(InputException.class, () -> validation.validateMovieID("TM123", "matrix"));
    }
}