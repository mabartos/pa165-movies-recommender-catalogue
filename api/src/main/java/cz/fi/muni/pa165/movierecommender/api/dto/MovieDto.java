package cz.fi.muni.pa165.movierecommender.api.dto;

import cz.fi.muni.pa165.movierecommender.api.dto.enums.Genre;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Maxim Svistunov
 */
@EqualsAndHashCode(callSuper = false)
@Data
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
    private Set<SimpleUserDto> favorites;
}
