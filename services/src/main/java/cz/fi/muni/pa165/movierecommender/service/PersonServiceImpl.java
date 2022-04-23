package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl extends GenericServiceImpl<Person> implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Person> findByName(String name) {
        return personDao.findByName(name);
    }

    @Override
    public EntityDao<Person> getEntityDao() {
        return personDao;
    }
}
