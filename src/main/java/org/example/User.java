package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a User with their name, ID, and a list of liked movie IDs.
 */
public record User(String id, String name, List<String> likedMovieIds) {
    /**
     * Constructs a new User object.
     * @param id            The unique ID of the user.
     * @param name          The name of the user.
     * @param likedMovieIds A list of movie IDs that the user likes.
     */
    public User(String id, String name, List<String> likedMovieIds) {
        this.id = id;
        this.name = name;
        this.likedMovieIds = new ArrayList<>(likedMovieIds);
    }

    @Override
    public List<String> likedMovieIds() {
        // Return a copy or unmodifiable list to protect encapsulation
        return new ArrayList<>(likedMovieIds);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", likedMovieIds=" + likedMovieIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id) && Objects.equals(new HashSet<>(this.likedMovieIds), new HashSet<>(user.likedMovieIds));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, likedMovieIds);
    }
}