package org.example;

import java.util.ArrayList;
import java.util.List;

public class Recommendation {
    private String userId;
    private String username;
    private List<String> recommendedMovieTitles;

    /**
     * Constructs a new Recommendation object.
     * @param userId                 The user's id.
     * @param username               The user's name
     * @param recommendedMovieTitles A list of titles of recommended movies
     */
    public Recommendation(String userId, String username, List<String> recommendedMovieTitles) {
        this.userId = userId;
        this.username = username;
        this.recommendedMovieTitles = new ArrayList<>(recommendedMovieTitles);
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRecommendedMovieTitles() {
        // Return a copy or unmodifiable list to protect encapsulation
        return new ArrayList<>(recommendedMovieTitles);
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", recommendedMovieTitles=" + recommendedMovieTitles +
                '}';
    }
}
