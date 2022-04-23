package cz.fi.muni.pa165.movierecommender.persistence.entity;

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
@Entity
@Table(name = "REVIEW")
public class Review extends GenericEntity {


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Movie.class)
    @NotNull
    @JoinColumn(name = "movie_id")
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

    /**
     * Average rating is automatically calculated as (scriptR. + ideaR. + visualsEditR. + musicR. + actingR)/5
     * It is crucial attribute for recommendation algorithm.
     */
    @NotNull
    @Min(1)
    @Max(10)
    private Double averageRating;

    @ManyToOne(optional=false)
    @NotNull
    private User author;

    public Review() {
    }

    public Review(User user, Movie movie, String text, Integer scriptRating, Integer ideaRating, Integer visualsEditRating, Integer musicRating, Integer actingRating, Double averageRating) {
        this.user = user;
        this.author = user;
        this.movie = movie;
        this.text = text;
        this.scriptRating = scriptRating;
        this.ideaRating = ideaRating;
        this.visualsEditRating = visualsEditRating;
        this.musicRating = musicRating;
        this.actingRating = actingRating;
        this.averageRating = averageRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getScriptRating() {
        return scriptRating;
    }

    public void setScriptRating(Integer scriptRating) {
        this.scriptRating = scriptRating;
    }

    public Integer getIdeaRating() {
        return ideaRating;
    }

    public void setIdeaRating(Integer ideaRating) {
        this.ideaRating = ideaRating;
    }

    public Integer getVisualsEditRating() {
        return visualsEditRating;
    }

    public void setVisualsEditRating(Integer visualsEditRating) {
        this.visualsEditRating = visualsEditRating;
    }

    public Integer getMusicRating() {
        return musicRating;
    }

    public void setMusicRating(Integer musicRating) {
        this.musicRating = musicRating;
    }

    public Integer getActingRating() {
        return actingRating;
    }

    public void setActingRating(Integer actingRating) {
        this.actingRating = actingRating;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return user.equals(review.user) && movie.equals(review.movie) && text.equals(review.text)
                && scriptRating.equals(review.scriptRating) && ideaRating.equals(review.ideaRating)
                && visualsEditRating.equals(review.visualsEditRating) && musicRating.equals(review.musicRating)
                && actingRating.equals(review.actingRating) && averageRating.equals(review.averageRating)
                && author.equals(review.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, movie, text, scriptRating, ideaRating, visualsEditRating,
                musicRating, actingRating, averageRating, author);
    }
}
