package cz.fi.muni.pa165.movierecommender.service.mapper.account;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.mapstruct.Mapper;

/**
 * @author Daniel Puchala
 */
@Mapper
public interface UserMapper {

    User toModel(UserDto dto);

    UserDto toDto(User user);
}
