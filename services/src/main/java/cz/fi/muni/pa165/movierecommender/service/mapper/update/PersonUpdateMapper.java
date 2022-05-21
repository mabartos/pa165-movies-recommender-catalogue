package cz.fi.muni.pa165.movierecommender.service.mapper.update;

import cz.fi.muni.pa165.movierecommender.api.update.PersonUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonUpdateMapper {

    Person toModel(PersonUpdateDto dto);

    PersonUpdateDto toDto(Person value);
}
