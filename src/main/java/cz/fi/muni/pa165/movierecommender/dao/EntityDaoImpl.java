package cz.fi.muni.pa165.movierecommender.dao;

import cz.fi.muni.pa165.movierecommender.entity.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Daniel Puchala
 */
public abstract class EntityDaoImpl<Entity extends GenericEntity> implements EntityDao<Entity> {

    @PersistenceContext
    protected EntityManager em;

    protected final Class<Entity> entityClass;

    public EntityDaoImpl(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(Entity entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Entity entity) {
        em.remove(entity);
    }

    @Override
    public void update(Entity entity) {
        em.merge(entity);
    }

    @Override
    public List<Entity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entity> query = cb.createQuery(entityClass);

        Root<Entity> entity = query.from(entityClass);
        query.select(entity);

        return em.createQuery(query).getResultList();
    }

    public Entity findById(Long id) {
        return em.find(entityClass, id);
    }
}
