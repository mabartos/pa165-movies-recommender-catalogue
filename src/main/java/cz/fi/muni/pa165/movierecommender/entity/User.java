package cz.fi.muni.pa165.movierecommender.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class User extends GenericEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Review.class)
    Set<Review> reviews;
}
