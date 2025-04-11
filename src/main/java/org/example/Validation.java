package org.example;
import java.util.List;
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
        users.forEach(this::uservalidation);
        movies.forEach(this::movievalidation);
    }

    @Override
    public void movievalidation(Movie movie) {

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
    private void ValidUserID(String ID){
     if(ID.length()!=9 ||!USER_ID_p.matcher(ID).matches()){
         throw new RuntimeException("ERROR: User ID " + ID +  "is wrong");
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
