package cz.fi.muni.pa165.movierecommender.service.mapper.create;

import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonCreateMapper {

    Person toModel(PersonCreateDto dto);

    PersonCreateDto toDto(Person value);
}
