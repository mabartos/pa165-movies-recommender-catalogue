package dao;

import entity.Person;

import java.util.List;

/**
 * @author Martin Bartos
 */
public interface PersonDao extends EntityDao<Person> {

    /**
     * Find all persons with similar name as the 'name' parameter
     *
     * @param name required person's name
     * @return list of persons
     */
    List<Person> findByName(String name);
}
