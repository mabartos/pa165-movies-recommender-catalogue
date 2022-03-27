package cz.fi.muni.pa165.movierecommender.entity;

import cz.fi.muni.pa165.movierecommender.enums.UserType;
import jakarta.validation.constraints.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing single user of the application.
 *
 * @author Daniel Puchala
 */

@Entity
public class User extends GenericEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Review.class)
    Set<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Movie.class)
    @JoinTable(
            name = "favorite_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    Set<Movie> favoriteMovies;

    @NotNull(message = "Email cannot be null")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Name cannot be null")
    @Column(unique = true)
    private String name;

    @NotNull
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String avatar;

    private String about;

    private LocalDateTime lastOnline;

    public User() {

    }

    public User(Set<Review> reviews, Set<Movie> favoriteMovies, String email, String name, String passwordHash,
                UserType userType, String avatar, String about, LocalDateTime lastOnline) {
        this.reviews = reviews;
        this.favoriteMovies = favoriteMovies;
        this.email = email;
        this.name = name;
        this.passwordHash = passwordHash;
        this.userType = userType;
        this.avatar = avatar;
        this.about = about;
        this.lastOnline = lastOnline;
    }

    public void createReview(Review review) {
        reviews.add(review);
    }

    public void deleteReview(Review review) {
        reviews.remove(review);
    }

    public void addFavorite(Movie movie) {
        favoriteMovies.add(movie);
    }

    public void removeFavorite(Movie movie) {
        favoriteMovies.remove(movie);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public LocalDateTime getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
    }

    @Override
    public String toString() {
        return String.format("User: Name = %s, Email = %s, User Type = %s", name, email, userType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        return Objects.equals(email, user.getEmail()) && Objects.equals(name, user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }

}
