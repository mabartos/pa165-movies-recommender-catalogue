package cz.fi.muni.pa165.movierecommender.api.facade;

import cz.fi.muni.pa165.movierecommender.api.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.update.PersonUpdateDto;

import java.util.List;

public interface PersonFacade extends GenericFacade<PersonDto, PersonCreateDto, PersonUpdateDto> {

    List<PersonDto> findByName(String name);
}
