package cz.fi.muni.pa165.movierecommender.api.update;

import cz.fi.muni.pa165.movierecommender.api.create.ReviewCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Petr Šlézar
 *
* Review DTO for its update - therefore contains also id. Does not call super equals and hash due to additional id
*/
@EqualsAndHashCode(callSuper = false)
@Data
public class ReviewUpdateDto extends ReviewCreateDto implements UpdateDto {

    @NotNull
    private Long id;
}
