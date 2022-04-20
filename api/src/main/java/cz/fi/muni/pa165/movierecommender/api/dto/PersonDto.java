package cz.fi.muni.pa165.movierecommender.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PersonDto implements Serializable {
    private String name;

    private LocalDate birth;

    private String about;

    private String picture;

    private Set<MovieDto> directedMovies;

    private Set<MovieDto> actedInMovies;

    public PersonDto(String name) {
        this.name = name;
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

    public Set<MovieDto> getDirectedMovies() {
        return directedMovies;
    }

    public void setDirectedMovies(Set<MovieDto> directedMovies) {
        this.directedMovies = directedMovies;
    }

    public Set<MovieDto> getActedInMovies() {
        return actedInMovies;
    }

    public void setActedInMovies(Set<MovieDto> actedInMovies) {
        this.actedInMovies = actedInMovies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDto personDto)) return false;
        return Objects.equals(getName(), personDto.getName())
                && Objects.equals(getBirth(), personDto.getBirth())
                && Objects.equals(getAbout(), personDto.getAbout())
                && Objects.equals(getPicture(), personDto.getPicture())
                && Objects.equals(getDirectedMovies(), personDto.getDirectedMovies())
                && Objects.equals(getActedInMovies(), personDto.getActedInMovies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirth(), getAbout(), getPicture(), getDirectedMovies(), getActedInMovies());
    }
}
