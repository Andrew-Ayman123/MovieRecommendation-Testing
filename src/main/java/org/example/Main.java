package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        FileHandler fileHandler = new FileHandler();

        List<User> users = fileHandler.readUsers("C://Users//karim//OneDrive//Desktop//SPRING 2025//Testing//project testing//MovieRecommendation-Testing//users.txt");
        List<Movie> movies = fileHandler.readMovies("C://Users//karim//OneDrive//Desktop//SPRING 2025//Testing//project testing//MovieRecommendation-Testing//movies.txt");

        System.out.println(movies);
        System.out.println(users);

        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation("userId1", "username1", List.of("Movie1", "Movie2")));

        fileHandler.writeOutput("recommendations.txt", recommendations, null);
    }

    public static String returnString() {
        return "Hello";
    }
}