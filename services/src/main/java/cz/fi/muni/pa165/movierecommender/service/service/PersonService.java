package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Martin Bartoš
 */
public interface PersonService extends GenericService<Person> {

    /**
     * Find all persons by a specific name
     *
     * @param name of a person corresponding potentially to more results
     * @return list of persons
     * @throws BadArgumentException when name parameter is null
     */
    List<Person> findByName(String name);
}
