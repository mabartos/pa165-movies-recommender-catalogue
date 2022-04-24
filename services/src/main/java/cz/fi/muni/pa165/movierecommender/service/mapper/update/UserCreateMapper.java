package cz.fi.muni.pa165.movierecommender.service.mapper.update;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.mapstruct.Mapper;

/**
 * @author Daniel Puchala
 */
@Mapper
public interface UserCreateMapper {

    User toModel(UserCreateDto dto);

    UserCreateDto toDto(User value);
}