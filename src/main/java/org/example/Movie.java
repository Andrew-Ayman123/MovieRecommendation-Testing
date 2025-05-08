package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Movie with its title, ID, and genres.
 */
public class Movie {
    private String id;
    private String title;
    private List<String> genres;

    public Movie(String id, String title, List<String> genres) {
        this.id = id;
        this.title = title;
        this.genres = new ArrayList<>(genres); // Defensive copy
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return new ArrayList<>(genres); // Return a copy to protect encapsulation
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(List<String> genres) {
        this.genres = new ArrayList<>(genres); // Defensive copy
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(title, movie.title) &&
               Objects.equals(id, movie.id) &&
               Objects.equals(new HashSet<>(this.genres), new HashSet<>(movie.genres));
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, new HashSet<>(genres));
    }
}
