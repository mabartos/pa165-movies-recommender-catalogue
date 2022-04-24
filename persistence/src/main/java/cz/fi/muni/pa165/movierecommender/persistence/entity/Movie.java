package cz.fi.muni.pa165.movierecommender.persistence.entity;

import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CollectionTable;
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
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "FILM_GENRE", joinColumns = @JoinColumn(name = "GENRE", referencedColumnName = "ID"))
    private Set<Genre> genres = new HashSet<>();

    @Lob
    @Column(length=1024)
    private String description;

    private Integer releaseYear;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", targetEntity = Review.class )
    private Set<Review> reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    public Person director;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> actors;

    public Set<Genre> getGenres() {
        return Optional.ofNullable(genres).orElseGet(HashSet::new);
    }

    public Set<Review> getReviews() {
        return Optional.ofNullable(reviews).orElseGet(HashSet::new);
    }

    public Set<Person> getActors() {
        return Optional.ofNullable(actors).orElseGet(HashSet::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        if (!super.equals(o)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name) && Objects.equals(duration, movie.duration) && Objects.equals(poster, movie.poster) && Objects.equals(genres, movie.genres) && Objects.equals(description, movie.description) && Objects.equals(releaseYear, movie.releaseYear)  && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, duration, poster, genres, description, releaseYear, director);
    }
}
