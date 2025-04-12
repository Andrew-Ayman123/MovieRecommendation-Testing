package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Validation validation = new Validation();
        FileHandler fileHandler = new FileHandler(validation);

        List<Movie> movies = null;
        List<User> users = null;
        try {
            users = fileHandler.readUsers("users.txt");
            movies = fileHandler.readMovies("movies.txt");
        } catch (InputException e) {
            System.out.println(e.getMessage());
            fileHandler.writeOutput("recommendations.txt", null, e.getMessage());
            return;
        }

        if (movies == null || users == null) return;

        RecommendationEngine recommendationEngine = new RecommendationEngine(movies);
        List<Recommendation> recommendations = new ArrayList<>();

        for (User user: users) {
            List<String> movieTitles = recommendationEngine.recommend(user);
            recommendations.add(new Recommendation(user.id(), user.name(), movieTitles));
        }

        fileHandler.writeOutput("recommendations.txt", recommendations, null);
    }
}