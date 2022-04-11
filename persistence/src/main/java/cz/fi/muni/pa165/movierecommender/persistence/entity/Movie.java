package cz.fi.muni.pa165.movierecommender.persistence.entity;

import cz.fi.muni.pa165.movierecommender.api.enums.Genre;
import jakarta.validation.constraints.NotNull;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Maxim Svistunov
 */
@Entity
@Table(name = "MOVIES")
public class Movie extends GenericEntity {
    @NotNull
    @Column(nullable = false)
    private String name;

    /**
     * Duration of the movie (in minutes)
     */
    private Integer duration;

    private String poster;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres = new HashSet<>();

    @Lob
    @Column(length=1024)
    private String description;

    private Integer releaseYear;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", targetEntity = Review.class )
    private Set<Review> reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private Person director;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> actors;

    @ManyToMany(mappedBy = "favoriteMovies")
    Set<User> favorites;

    public Movie() {}

    public Person getDirector() {
        return director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String caption) {
        this.description = caption;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Set<Genre> getGenres() {
        return Optional.ofNullable(genres).orElseGet(HashSet::new);
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Review> getReviews() {
        return Optional.ofNullable(reviews).orElseGet(HashSet::new);
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Set<Person> getActors() {
        return Optional.ofNullable(actors).orElseGet(HashSet::new);
    }

    public void setActors(Set<Person> actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.getName()) && duration.equals(movie.getDuration())
                && poster.equals(movie.getPoster()) && genres.equals(movie.getGenres())
                && description.equals(movie.getDescription()) && releaseYear.equals(movie.getReleaseYear())
                && reviews.equals(movie.getReviews()) && director.equals(movie.getDirector())
                && actors.equals(movie.getActors());

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, genres, description, releaseYear, reviews, director, actors);
    }
}
