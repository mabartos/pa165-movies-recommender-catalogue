package cz.fi.muni.pa165.movierecommender.api.dto.update;

import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = false)
@Data
public class ReviewUpdateDto extends ReviewCreateDto implements UpdateDto {

    @NotNull
    private Long id;
}
