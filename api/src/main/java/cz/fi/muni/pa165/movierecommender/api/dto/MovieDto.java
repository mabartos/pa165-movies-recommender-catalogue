package cz.fi.muni.pa165.movierecommender.api.dto;

import cz.fi.muni.pa165.movierecommender.api.enums.Genre;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Maxim Svistunov
 */
public class MovieDto implements Serializable {

    private String name;
    private Integer duration;
    private String poster;
    private Set<Genre> genres = new HashSet<>();
    private String description;
    private Integer releaseYear;
    private Set<ReviewDto> reviews;
    private PersonDto director;
    private Set<PersonDto> actors;
    private Set<UserDto> favorites;

    public MovieDto() {}

    public PersonDto getDirector() {
        return director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String caption) {
        this.description = caption;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Set<Genre> getGenres() {
        return Optional.ofNullable(genres).orElseGet(HashSet::new);
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<ReviewDto> getReviews() {
        return Optional.ofNullable(reviews).orElseGet(HashSet::new);
    }

    public void setReviews(Set<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public void setDirector(PersonDto director) {
        this.director = director;
    }

    public Set<PersonDto> getActors() {
        return Optional.ofNullable(actors).orElseGet(HashSet::new);
    }

    public void setActors(Set<PersonDto> actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDto)) return false;
        MovieDto movieDto = (MovieDto) o;
        return name.equals(movieDto.getName()) && duration.equals(movieDto.getDuration())
                && poster.equals(movieDto.getPoster()) && genres.equals(movieDto.getGenres())
                && description.equals(movieDto.getDescription()) && releaseYear.equals(movieDto.getReleaseYear())
                && reviews.equals(movieDto.getReviews()) && director.equals(movieDto.getDirector())
                && actors.equals(movieDto.getActors());

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, genres, description, releaseYear, reviews, director, actors);
    }

}
