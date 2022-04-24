package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.SimpleUserDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleUserMapper {

    User toModel(SimpleUserDto dto);

    SimpleUserDto toDto(User value);
}