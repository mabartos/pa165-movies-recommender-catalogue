package cz.fi.muni.pa165.movierecommender.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie extends GenericEntity {

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

    public Person getDirector() {
        return director;
    }
}
