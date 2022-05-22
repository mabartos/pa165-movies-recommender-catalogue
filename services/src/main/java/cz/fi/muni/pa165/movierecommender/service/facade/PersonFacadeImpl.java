package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.PersonUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.service.mapper.PersonMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.create.PersonCreateMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.update.PersonUpdateMapper;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import cz.fi.muni.pa165.movierecommender.service.service.PersonService;
import cz.fi.muni.pa165.movierecommender.api.facade.PersonFacade;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonFacadeImpl extends GenericFacadeImpl<Person, PersonDto, PersonCreateDto, PersonUpdateDto> implements PersonFacade {
    private final PersonService personService;

    private final PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);
    private final PersonCreateMapper personCreateMapper = Mappers.getMapper(PersonCreateMapper.class);
    private final PersonUpdateMapper personUpdateMapper = Mappers.getMapper(PersonUpdateMapper.class);

    @Autowired
    public PersonFacadeImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @Transactional
    public PersonDto create(PersonCreateDto createDto) {
        Person newPerson = mapToCreatedEntity(createDto);

        return mapToDto(personService.create(newPerson));
    }

    @Override
    @Transactional
    public PersonDto update(PersonUpdateDto updateDto) {
        Person updatedPerson = mapToUpdatedEntity(updateDto);

        return mapToDto(personService.update(updatedPerson));
    }

    @Override
    protected GenericService<Person> service() {
        return personService;
    }

    @Override
    protected Person mapToCreatedEntity(PersonCreateDto dto) {
        return personCreateMapper.toModel(dto);
    }

    @Override
    protected PersonDto mapToDto(Person entity) {
        return personMapper.toDto(entity);
    }

    @Override
    protected Person mapToUpdatedEntity(PersonUpdateDto dto) {
        return personUpdateMapper.toModel(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findByName(String name) {
        if (name.isEmpty()) return Collections.emptyList();

        List<Person> persons = personService.findByName(name);

        return persons.stream().map(personMapper::toDto).collect(Collectors.toList());
    }
}
