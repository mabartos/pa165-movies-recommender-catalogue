package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person> implements PersonService {

    @Autowired
    private PersonDao personDao;
    public PersonServiceImpl(PersonDao personDao) {
        super(personDao,Person.class);
        this.personDao = personDao;
    }

    @Override
    public List<Person> findByName(String name) {
        return personDao.findByName(name);
    }
}
