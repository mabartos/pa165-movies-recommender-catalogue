package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.PersonUpdateDto;

import java.util.List;

public interface PersonFacade extends GenericFacade<PersonCreateDto, PersonUpdateDto> {

    List<PersonDto> findByName(String name);
}