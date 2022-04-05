package cz.fi.muni.pa165.movierecommender.dao;

import cz.fi.muni.pa165.movierecommender.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Martin Bartos
 */
@Repository
public class PersonDaoImpl extends EntityDaoImpl<Person> implements PersonDao {

    public PersonDaoImpl() {
        super(Person.class);
    }

    @Override
    public List<Person> findByName(String name) {
        return em.createQuery("select p from Person p where p.name like :name", Person.class)
                .setParameter("name", name)
                .getResultList();
    }
}
