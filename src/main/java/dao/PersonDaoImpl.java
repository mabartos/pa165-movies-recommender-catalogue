package dao;

import entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Person> query = cb.createQuery(Person.class);
        final Root<Person> person = query.from(Person.class);

        query.select(person).where(cb.like(person.get(name), name));

        return em.createQuery(query).getResultList();
    }
}
