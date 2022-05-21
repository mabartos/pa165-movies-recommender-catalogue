package cz.fi.muni.pa165.movierecommender.api.account;

import cz.fi.muni.pa165.movierecommender.api.update.UpdateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserUpdateDto extends UserCreateDto implements UpdateDto {

    private Long Id;

}
