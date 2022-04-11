package cz.fi.muni.pa165.movierecommender.persistence.dao;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Martin Bartos
 */
@Transactional
public interface PersonDao extends EntityDao<Person> {

    /**
     * Find all persons with similar name as the 'name' parameter
     *
     * @param name required person's name
     * @return list of persons
     */
    List<Person> findByName(String name);
}
