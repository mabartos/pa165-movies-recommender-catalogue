package cz.fi.muni.pa165.movierecommender.service.mapper.update;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.mapstruct.Mapper;

/**
 * @author Daniel Puchala
 */
@Mapper
public interface UserUpdateMapper {

    User toModel(UserUpdateDto dto);

    UserUpdateDto toDto(User value);
}
