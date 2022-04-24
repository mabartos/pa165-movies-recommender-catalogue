package cz.fi.muni.pa165.movierecommender.api.dto.account;

import cz.fi.muni.pa165.movierecommender.api.dto.create.CreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.UpdateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserUpdateDto implements UpdateDto, CreateDto {

    private Long Id;
}
