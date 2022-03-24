package dao;

import entity.GenericEntity;

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

    public void create(Entity entity) {
        em.persist(entity);
    }

    public void delete(Entity entity) {
        em.remove(entity);
    }

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

    public long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        query.select(cb.count(query.from(entityClass)));

        return em.createQuery(query).getSingleResult();
    }
}
