package cz.fi.muni.pa165.movierecommender.api.dto.update;

import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Petr Šlézar
 * <p>
 * Review DTO for its update - therefore contains also id. Does not call super equals and hash due to additional id
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ReviewUpdateDto extends ReviewCreateDto implements UpdateDto {

    @NotNull
    private Long id;
}
