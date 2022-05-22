package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.SimplePersonDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.mapstruct.Mapper;

@Mapper(uses = {SimpleMovieMapper.class})
public interface SimplePersonMapper {

    Person toModel(SimplePersonDto dto);

    SimplePersonDto toDto(Person value);
}