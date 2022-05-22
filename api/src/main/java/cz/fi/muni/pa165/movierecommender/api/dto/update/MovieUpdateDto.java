package cz.fi.muni.pa165.movierecommender.api.dto.update;

import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Maxim Svistunov
 * <p>
 * Movie DTO for update
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class MovieUpdateDto extends MovieCreateDto implements UpdateDto {

    @NotNull
    private Long id;
}
