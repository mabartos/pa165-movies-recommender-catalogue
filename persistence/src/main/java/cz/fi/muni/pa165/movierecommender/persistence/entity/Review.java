package cz.fi.muni.pa165.movierecommender.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;


/**
 * Entity that represents a review for a movie submitted by a particular user.
 * Basically a join table symbolizing many-to-many relationship between user
 * and a movie.
 *
 * @author Petr Slezar 485504@mail.muni.cz
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REVIEW")
public class Review extends GenericEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @NotNull
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"reviews"},allowSetters = true)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Movie.class)
    @NotNull
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties(value = {"reviews"},allowSetters = true)
    private Movie movie;

    /**
     * The review can also contain user's text. However, only important parts are ratings.
     * The text can be blank, but not null.
     */
    @NotNull
    private String text;

    /**
     * All ratings are withing a whole number scale ranging from 1 to 10.
     * Where 1 is the worst and 10 the best. The rating cannot be null.
     */
    @NotNull
    @Min(1)
    @Max(10)
    private Integer scriptRating;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer ideaRating;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer visualsEditRating;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer musicRating;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer actingRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return Objects.equals(user, review.user) && Objects.equals(movie, review.movie) && Objects.equals(text, review.text) && Objects.equals(scriptRating, review.scriptRating) && Objects.equals(ideaRating, review.ideaRating) && Objects.equals(visualsEditRating, review.visualsEditRating) && Objects.equals(musicRating, review.musicRating) && Objects.equals(actingRating, review.actingRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, movie, text, scriptRating, ideaRating, visualsEditRating, musicRating, actingRating);
    }
}
