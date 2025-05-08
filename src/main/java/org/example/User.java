package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Represents a User with their name, ID, and a list of liked movie IDs.
 */
public class User {
    private String id;
    private String name;
    private List<String> likedMovieIds;

    public User(String id, String name, List<String> likedMovieIds) {
        this.id = id;
        this.name = name;
        this.likedMovieIds = new ArrayList<>(likedMovieIds); // Defensive copy
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getLikedMovieIds() {
        return new ArrayList<>(likedMovieIds); // Defensive copy
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLikedMovieIds(List<String> likedMovieIds) {
        this.likedMovieIds = new ArrayList<>(likedMovieIds); // Defensive copy
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
        if (!(o instanceof User user)) return false;
        return Objects.equals(name, user.name) &&
               Objects.equals(id, user.id) &&
               Objects.equals(new HashSet<>(this.likedMovieIds), new HashSet<>(user.likedMovieIds));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, new HashSet<>(likedMovieIds));
    }
}
