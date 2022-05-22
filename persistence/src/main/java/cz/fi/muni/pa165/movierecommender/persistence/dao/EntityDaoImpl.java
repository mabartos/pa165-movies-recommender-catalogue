package cz.fi.muni.pa165.movierecommender.persistence.dao;

import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Daniel Puchala
 */
@NoRepositoryBean
public abstract class EntityDaoImpl<Entity extends GenericEntity> implements EntityDao<Entity> {

    protected final Class<Entity> entityClass;
    @PersistenceContext
    protected EntityManager em;

    public EntityDaoImpl(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Entity create(Entity entity) {

        if (entity == null) throw new IllegalArgumentException("Cannot create null entity");

        if (entity.getId() != null && findById(entity.getId()) != null)
            throw new EntityExistsException("Entity of type " + entityClass.getName() +
                    " with id " + entity.getId() + " already exists");

        em.persist(entity);
        return entity;
    }

    @Override
    public void delete(Entity entity) {

        if (entity == null) throw new IllegalArgumentException("Entity to delete is null");

        if (entity.getId() != null && findById(entity.getId()) == null)
            throw new EntityNotFoundException("Cannot delete non-existent entity");

        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public Entity update(Entity entity) {

        if (entity == null) throw new IllegalArgumentException("Entity to update is null");

        if (entity.getId() != null && findById(entity.getId()) == null)
            throw new EntityNotFoundException("Cannot update non-existent entity");

        return em.merge(entity);
    }

    @Override
    public List<Entity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entity> query = cb.createQuery(entityClass);

        Root<Entity> entity = query.from(entityClass);
        query.select(entity);

        return em.createQuery(query).getResultList();
    }

    @Override
    public Entity findById(Long id) {

        if (id == null) throw new IllegalArgumentException("Id is null");

        return em.find(entityClass, id);
    }

    @Override
    public Long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        query.select(cb.count(query.from(entityClass)));

        return em.createQuery(query).getSingleResult();
    }
}
