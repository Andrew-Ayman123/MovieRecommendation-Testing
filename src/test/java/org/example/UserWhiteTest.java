package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserWhiteTest {

    @Test
    public void testSetAndGetName() {
        User user = new User("12345678A", "Alice", List.of("TM123", "TC789"));
        user.setName("Bob");
        assertEquals("Bob", user.getName());
    }

    @Test
    public void testSetAndGetId() {
        User user = new User("12345678A", "Alice", List.of("TM123", "TC789"));
        user.setId("98765432B");
        assertEquals("98765432B", user.getId());
    }

    @Test
    public void testSetAndGetLikedMovies() {
        User user = new User("12345678A", "Alice", List.of("TM123", "TC789"));
        user.setLikedMovieIds(List.of("LM001", "LM002"));
        assertEquals(List.of("LM001", "LM002"), user.getLikedMovieIds());
    }

    @Test
    public void testNotNullUser() {
        User user = new User("12345678A", "Alice", List.of("TM123", "TC789"));
        assertNotNull(user);
    }

    @Test
    public void testToStringFormat() {
        User user = new User("12345678A", "Alice", List.of("TM123", "TC789"));
        String expected = "User{name='Alice', id='12345678A', likedMovieIds=[TM123, TC789]}";
        assertEquals(expected, user.toString());
    }
}