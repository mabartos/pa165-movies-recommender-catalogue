package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.mapstruct.Mapper;

@Mapper(uses = {MovieMapper.class})
public interface PersonMapper {
    Person toModel(PersonDto dto);

    PersonDto toDto(Person value);
}
