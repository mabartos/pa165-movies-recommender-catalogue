package cz.fi.muni.pa165.movierecommender.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Martin Bartos
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Table(name = "PERSONS")
public class Person extends GenericEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDate birth;

    @Column
    private String about;

    @Column
    private String picture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "director", targetEntity = Movie.class)
    private Set<Movie> directedMovies;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors", targetEntity = Movie.class)
    private Set<Movie> actedInMovies;

    public Set<Movie> getDirectedMovies() {
        return Optional.ofNullable(directedMovies).orElseGet(HashSet::new);
    }

    public Set<Movie> getActedInMovies() {
        return Optional.ofNullable(actedInMovies).orElseGet(HashSet::new);
    }
}