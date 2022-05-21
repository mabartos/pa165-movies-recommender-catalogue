package cz.fi.muni.pa165.movierecommender.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Petr Šlézar
 *
 * A Review DTO for server's respose to GET
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ReviewDto extends GenericEntityDto {

    private SimpleUserDto user;
    private SimpleMovieDto movie;
    private String text;
    private Integer scriptRating;
    private Integer ideaRating;
    private Integer visualsEditRating;
    private Integer musicRating;
    private Integer actingRating;
}
