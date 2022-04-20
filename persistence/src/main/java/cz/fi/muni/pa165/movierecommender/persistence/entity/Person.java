package cz.fi.muni.pa165.movierecommender.persistence.entity;

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

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<Movie> getDirectedMovies() {
        return Optional.ofNullable(directedMovies).orElseGet(HashSet::new);
    }

    public void setDirectedMovies(Set<Movie> directedMovies) {
        this.directedMovies = directedMovies;
    }

    public Set<Movie> getActedInMovies() {
        return Optional.ofNullable(actedInMovies).orElseGet(HashSet::new);
    }

    public void setActedInMovies(Set<Movie> actedInMovies) {
        this.actedInMovies = actedInMovies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof final Person person)) return false;

        return getName().equals(person.getName()) &&
                Objects.equals(getBirth(), person.getBirth()) &&
                Objects.equals(getAbout(), person.getAbout()) &&
                Objects.equals(getPicture(), person.getPicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirth(), getAbout(), getPicture());
    }
}
