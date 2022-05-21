package cz.fi.muni.pa165.movierecommender.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
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

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "director", targetEntity = Movie.class)
    @JsonIgnoreProperties(value = {"director","actors","reviews"},allowSetters = true)
    private Set<Movie> directedMovies;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors", targetEntity = Movie.class)
    @JsonIgnoreProperties(value = {"director","actors","reviews"},allowSetters = true)
    private Set<Movie> actedInMovies;

    public Set<Movie> getDirectedMovies() {
        return Optional.ofNullable(directedMovies).orElseGet(HashSet::new);
    }

    public Set<Movie> getActedInMovies() {
        return Optional.ofNullable(actedInMovies).orElseGet(HashSet::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birth, person.birth) && Objects.equals(about, person.about) && Objects.equals(picture, person.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, birth, about, picture);
    }
}
