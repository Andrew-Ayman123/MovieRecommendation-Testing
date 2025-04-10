package org.example;

import java.util.List;
// class interface 3ashan hayeraga3lena el movies eli kol user 3agbo men genre mo3ayana

public interface RecommandationsI {
    List<Movie> recommends(User user, List<Movie> movies);
}
