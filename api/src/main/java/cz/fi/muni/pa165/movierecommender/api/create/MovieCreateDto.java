package cz.fi.muni.pa165.movierecommender.api.create;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Maxim Svistunov
 *
 * A class for creating a movie
 */
@Data
public class MovieCreateDto implements CreateDto {

    @NotNull
    private String name;
    @NotNull
    private Integer duration;
    private String poster;
    private Set<String> genres = new HashSet<>();
    private String description;
    @Min(1)
    private Integer releaseYear;
    private Set<Long> reviewsIds;
    private Long directorId;
    private Set<Long> actorsIds;

}
