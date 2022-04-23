package cz.fi.muni.pa165.movierecommender.api.dto.create;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReviewCreateDto implements Serializable {

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
