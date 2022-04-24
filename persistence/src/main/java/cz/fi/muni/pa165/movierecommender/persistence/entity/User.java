package cz.fi.muni.pa165.movierecommender.persistence.entity;

import cz.fi.muni.pa165.movierecommender.api.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing single user of the application.
 *
 * @author Daniel Puchala
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "USERS")
public class User extends GenericEntity {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Review.class)
    private final Set<Review> reviews = new HashSet<>();

    public void createReview(Review review) {
        reviews.add(review);
    }

    public void deleteReview(Review review) {
        reviews.remove(review);
    }

}
