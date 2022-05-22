package cz.fi.muni.pa165.movierecommender.api.dto.create;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Petr Šlézar
 * <p>
 * A class for creating a review - main difference is containing only author's and movie's id
 */
@Data
public class ReviewCreateDto implements CreateDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    @NotNull
    private String text;

    @NotNull
    @Min(0)
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
}
