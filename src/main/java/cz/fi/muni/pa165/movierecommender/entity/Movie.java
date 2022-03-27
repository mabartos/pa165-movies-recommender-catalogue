package cz.fi.muni.pa165.movierecommender.entity;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Maxim Svistunov
 */
@Entity
public class Movie extends GenericEntity {

    enum Genre {
        DRAMA,
        COMEDY,
        ACTION,
        THRILLER,
        ROMANCE,
        HORROR,
        CRIME,
        ADVENTURE,
        MYSTERY,
        FAMILY,
        FANTASY,
        SCI_FI,
        MUSIC,
        ANIMATION,
        BIOGRAPHY,
        HISTORY,
        WAR,
        SPORT,
        WESTERN,
        SLICE_OF_LIFE
    }

    @NotNull
    @Column(nullable = false)
    private String name;

    private int runtimeMin;

    private String poster;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres = new HashSet<>();

    @Lob
    @Column(length=1024)
    private String description;

    private Integer releaseYear;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", targetEntity = Review.class )
    Set<Review> reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private Person director;

    @ManyToMany
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> actors;

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

    public int getRuntimeMin() {
        return runtimeMin;
    }

    public void setRuntimeMin(int runtimeMin) {
        this.runtimeMin = runtimeMin;
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
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Set<Person> getActors() {
        return actors;
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
        if (!(o instanceof Movie movie)) return false;

        if (runtimeMin != movie.runtimeMin) return false;
        if (!name.equals(movie.name)) return false;
        if (!Objects.equals(genres, movie.genres)) return false;
        if (!Objects.equals(description, movie.description)) return false;
        if (!Objects.equals(releaseYear, movie.releaseYear)) return false;
        if (!Objects.equals(reviews, movie.reviews)) return false;
        if (!Objects.equals(director, movie.director)) return false;
        return Objects.equals(actors, movie.actors);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + runtimeMin;
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        return result;
    }
}
