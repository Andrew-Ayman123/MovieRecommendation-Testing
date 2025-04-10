package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Movie with its title, ID, and genres.
 */
public record Movie(String id, String title, List<String> genres) {
    /**
     * Constructs a new Movie object.
     *
     * @param id     The unique ID of the movie.
     * @param title  The title of the movie.
     * @param genres A list of genres associated with the movie.
     */
    public Movie(String id, String title, List<String> genres) {
        this.id = id;
        this.title = title;
        this.genres = new ArrayList<>(genres);
    }

    @Override
    public List<String> genres() {
        // Return a copy or unmodifiable list to protect encapsulation
        return new ArrayList<>(genres);
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
        return Objects.equals(title(), movie.title()) && Objects.equals(id(), movie.id()) && Objects.equals(new HashSet<>(this.genres), new HashSet<>(movie.genres));
    }

    @Override
    public int hashCode() {
        return Objects.hash(title(), id(), genres());
    }
}