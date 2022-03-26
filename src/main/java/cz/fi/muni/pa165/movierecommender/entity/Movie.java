package cz.fi.muni.pa165.movierecommender.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie extends GenericEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", targetEntity = Review.class )
    Set<Review> reviews;

    @ManyToOne
    private Person director;

    @ManyToMany
    private Set<Person> actors;

    public Person getDirector() {
        return director;
    }
}
