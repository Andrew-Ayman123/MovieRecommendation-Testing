package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

<<<<<<< HEAD
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

//here I used private final instead of record
public class Movie {
    private final String title;
    private final String id;
    private final List<String> genres;

    // Constructor
    public Movie(String title, String id, List<String> genres) {
        this.title = title;
        this.id = id;
        this.genres = genres;
    }

    // Getters
    public String getTitle() {
        return title;
=======
/**
 * Represents a Movie with its title, ID, and genres.
 */
public class Movie {
    private String id;
    private String title;
    private List<String> genres;

    /**
     * Constructs a new Movie object.
     * @param id     The unique ID of the movie.
     * @param title  The title of the movie.
     * @param genres A list of genres associated with the movie.
     */
    public Movie(String id, String title, List<String> genres) {
        this.id = id;
        this.title = title;
        this.genres = new ArrayList<>(genres);
>>>>>>> 88cdd94 (add file handler class)
    }

    public String getId() {
        return id;
    }

<<<<<<< HEAD
    public List<String> getGenres() {
        return genres;
=======
    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
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
>>>>>>> 88cdd94 (add file handler class)
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(getTitle(), movie.getTitle()) && Objects.equals(getId(), movie.getId()) && Objects.equals(new HashSet<>(this.genres), new HashSet<>(movie.genres));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getId(), getGenres());
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 88cdd94 (add file handler class)
