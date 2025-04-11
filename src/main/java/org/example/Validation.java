package org.example;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//we used inteface to make unit testing easier
public class Validation implements UserValidation,MovieValidation{
    private final List<Movie> movies;
    private final List<User> users;
    private static final Pattern USER_ID_p = Pattern.compile("^\\d{8}[A-Z]");


    public Validation(List<Movie> movies, List<User> users) {
        this.movies = movies;
        this.users = users;
    }
    public void valid(){
        validateUserIdUniqueness();
        users.forEach(this::uservalidation);
        validMovieIdUniqueness();
        movies.forEach(this::movievalidation);
    }

    @Override
    public void movievalidation(Movie movie) {
        validateMovieTitle(movie.title());
        validateGenre(movie.genres());


    }
    private void validateUserIdUniqueness() {
        Set<String> seenIds = new HashSet<>();
        for (User user : users) {
            String userId = user.id();
            if (seenIds.contains(userId)) {
                throw new RuntimeException("User ID " + userId + " is not unique");
            }
            seenIds.add(userId);
        }
    }
    private void validMovieIdUniqueness(){
        for(int i=0;i<movies.size();i++){
            Movie Cmovie=movies.get(i);
            String LastThreeDigits=getLastThreeDigits(Cmovie.id());
            for(int j=i+1;j<movies.size();j++){
                Movie Nmovie=movies.get(j);
                String LastThreeDigits2=getLastThreeDigits(Nmovie.id());
                if(LastThreeDigits.equals(LastThreeDigits2)){
                    throw new RuntimeException("Movie ID numbers " + Cmovie.id() + " aren't unique");
                }
            }
        }
    }
    private String getLastThreeDigits(String movieId) {
        if (movieId == null || movieId.length() < 3) {
            throw new RuntimeException("Invalid Movie ID format: " + movieId);
        }
        return movieId.substring(movieId.length() - 3);
    }




    @Override
    public void uservalidation(User user) {
        ValidUsername(user.name());
        ValidUserID(user.id());
    }
    private void ValidUsername(String name){
        if(name==null|| name.isEmpty() || name.startsWith(" ") ||!name.matches("[A-Za-z]+")){
            throw new RuntimeException("Error: User Name "+ name+ "Is Wrong");
        }
        validNameOrTitle(name,false);
    }
    private void validateMovieTitle(String title){
        if(title==null||title.isEmpty()){
            throw new RuntimeException("Movie title is empty");
        }
        validNameOrTitle(title,true);
    }

    private void ValidUserID(String ID){
     if(ID.length()!=9 ||!USER_ID_p.matcher(ID).matches()){
         throw new RuntimeException("ERROR: User ID " + ID +  "is wrong");
     }

    }
    private void validateGenre(List<String> genres){
        if(genres==null ||genres.isEmpty()){
            throw new RuntimeException("Error:Movie genre is empty");
        }
    }



    private void validNameOrTitle(String NT,boolean isTitle){
        if (NT == null || NT.isEmpty()) {
            throw new RuntimeException("ERROR: Name or title is empty");
        }

        String[] words = NT.split(" ");
        for (String word : words) {
            if (!Character.isUpperCase(word.charAt(0))) {
                if (isTitle) {
                    throw new RuntimeException("ERROR: Movie title " + NT + " is wrong");
                } else {
                    throw new RuntimeException("ERROR: User Name " + NT + " is wrong");
                }
            }
        }


    }

}
