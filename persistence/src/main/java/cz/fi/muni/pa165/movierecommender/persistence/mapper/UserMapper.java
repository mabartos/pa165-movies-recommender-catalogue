package cz.fi.muni.pa165.movierecommender.persistence.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.UserDto;
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
