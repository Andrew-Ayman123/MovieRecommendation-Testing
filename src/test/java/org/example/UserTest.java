package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testEqualMethod(){
        User karim = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        User karim2 = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        User karim3 = new User("2001440", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
         assertTrue(karim.equals(karim2));
         assertFalse(karim.equals(karim3));
    }
   // same likedMovies but different order
    @Test
    public void testEqualMethod2() {
        User karim = new User("2000318", "Karim Elnekheily", List.of("SA002", "KAR001", "MA003"));
        User karim2 = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        assertTrue(karim.equals(karim2));
    }

    @Test
    public void differentOrderLikedMovies() {
        User karim = new User("2000318", "Karim Elnekheily", List.of("SA002", "KAR001", "MA003"));
        User karim2 = new User("2001440", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        assertNotEquals(karim, karim2);
    }


    @Test
    public void TesttoString() {
        User karim = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        String exp = "User{name='Karim Elnekheily', id='2000318', likedMovieIds=[KAR001, SA002, MA003]}";
        assertEquals(exp, karim.toString());
    }

    @Test
    public void sameAttributes() {
        User karim = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        User karim2 = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        assertEquals(karim, karim2);
    }

    @Test
    public void differentName(){
        User saad = new User("2000318", "saad", List.of("KAR001", "SA002", "MA003"));
        User karim = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        assertNotEquals(saad, karim);
    }

    @Test
    public void differentId() {
        User karim = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        User karim2 = new User("2001440", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        assertNotEquals(karim, karim2);
    }

    @Test
    public void differentLikedMovieIds() {
        User karim = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        User karim1 = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA102", "MA003"));
        assertNotEquals(karim, karim1);
    }

    @Test
    public void nullObject() {
        User karim = new User("2000318", "Karim Elnekheily", List.of("KAR001", "SA002", "MA003"));
        assertNotEquals(null, karim);
    }




}
