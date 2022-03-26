package cz.fi.muni.pa165.movierecommender.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;

/**
 * @author Petr Slezar
 */
@Entity
@Table(name = "REVIEW")
public class Review extends GenericEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @NotNull
    private String text;

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

    /*
    User for finding best movies in recommendation
     */
    @NotNull
    @Min(1)
    @Max(10)
    private Integer averageRating;

    @ManyToOne(optional=false)
    @NotNull
    private User author;

    public Review() {
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
}
