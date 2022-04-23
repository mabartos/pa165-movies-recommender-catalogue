package cz.fi.muni.pa165.movierecommender.api.dto.update;

import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
public class ReviewUpdateDto extends ReviewCreateDto {

    @NotNull
    private Long id;
}
