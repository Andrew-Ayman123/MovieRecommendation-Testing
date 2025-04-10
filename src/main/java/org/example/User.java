package org.example;
import java.util.List;
import java.util.Objects;
import java.util.HashSet;



// esta3melt record 3ashan teb2a immutable bs momken nesta3mel private Final instead
// w record betdina el equals method gahza
public record User(String name,String id,List<String> favouriteMovieIDS) {

/* we can t use equals in favouriteMoviesId by passing object
because it compares by order
so hashset mesh byehtam bel order
 */
//we use function equals to check if we are already added the user before ,Preventing Duplicate users
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id) && Objects.equals(new HashSet<>(this.favouriteMovieIDS), new HashSet<>(user.favouriteMovieIDS));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, favouriteMovieIDS);
    }
    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' +
                ", id='" + id + '\'' + ", favouriteMovieIds=" + favouriteMovieIDS +
                '}';
    }


}
