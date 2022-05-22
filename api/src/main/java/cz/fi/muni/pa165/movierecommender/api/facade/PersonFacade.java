package cz.fi.muni.pa165.movierecommender.api.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.PersonUpdateDto;

import java.util.List;

public interface PersonFacade extends GenericFacade<PersonDto, PersonCreateDto, PersonUpdateDto> {

    List<PersonDto> findByName(String name);

    /**
     * Creates a person entity. The dto of newly created person contains no info about
     * directed or acted in movies - we must assign this information during the movie
     * creation or its update.
     *
     * @param createDto entity's create DTO, not null
     * @return DTO of newly created person
     */
    @Override
    PersonDto create(PersonCreateDto createDto);

    /**
     * Updates a person entity. The behaviour is same asi with create() method.
     *
     * @param updateDto entity's update DTO, not null
     * @return DTO of newly updated person
     */
    @Override
    PersonDto update(PersonUpdateDto updateDto);
}
