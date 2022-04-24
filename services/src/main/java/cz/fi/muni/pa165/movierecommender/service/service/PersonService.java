package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService extends GenericService<Person> {

    List<Person> findByName(String name);
}
