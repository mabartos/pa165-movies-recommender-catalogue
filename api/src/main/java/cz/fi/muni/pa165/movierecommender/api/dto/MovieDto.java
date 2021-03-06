package cz.fi.muni.pa165.movierecommender.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maxim Svistunov
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class MovieDto extends GenericEntityDto {

    private String name;
    private Integer duration;
    private String poster;
    private Set<String> genres = new HashSet<>();
    private String description;
    private Integer releaseYear;
    private Set<SimpleReviewDto> reviews;
    private PersonDto director;
    private Set<SimpleMovieDto> actors;
}
