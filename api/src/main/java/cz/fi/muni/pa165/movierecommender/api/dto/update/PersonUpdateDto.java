package cz.fi.muni.pa165.movierecommender.api.dto.update;

import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = false)
@Data
public class PersonUpdateDto extends PersonCreateDto implements UpdateDto {

    @NotNull
    private Long id;
}
