package cz.fi.muni.pa165.movierecommender.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Petr Šlézar
 *
 * This class is intended to be used for simplifying the info about movie that is carried by other DTO (we don't need
 * such level of detail when displaying review,etc.)
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleMovieDto extends GenericEntityDto {

    private String name;
    private Integer duration;
    private String poster;
    private Integer releaseYear;

}
