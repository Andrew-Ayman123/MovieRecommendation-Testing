package org.example;


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
    }

    public String getId() {
        return id;
    }

    public List<String> getGenres() {
        return genres;
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
}
