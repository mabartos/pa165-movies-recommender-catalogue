package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;

import java.util.List;

/**
 * @param <Entity> of type generic entity
 * @author Daniel Puchala
 */
public abstract class GenericServiceImpl<Entity extends GenericEntity> implements GenericService<Entity> {

    protected final Class<Entity> entityClass;
    private final EntityDao<Entity> entityDao;

    public GenericServiceImpl(EntityDao<Entity> entityDao, Class<Entity> entityClass) {
        this.entityDao = entityDao;
        this.entityClass = entityClass;
    }

    @Override
    public Entity create(Entity entity) {
        if (entity == null) throw new BadArgumentException("Provided Entity is null");

        Entity fromDb = entityDao.findById(entity.getId());
        if (fromDb != null) throw new BadArgumentException("Provided Entity already exists");

        return entityDao.create(entity);
    }


    @Override
    public void delete(Entity entity) {

        if (entity == null) throw new BadArgumentException("Provided Entity is null");

        Entity fromDb = entityDao.findById(entity.getId());
        if (fromDb == null) throw new MissingEntityException(entityClass, entity.getId());

        entityDao.delete(entity);
    }

    @Override
    public Entity update(Entity entity) {
        if (entity == null) throw new BadArgumentException("Provided Entity is null");

        Entity fromDb = entityDao.findById(entity.getId());
        if (fromDb == null) throw new MissingEntityException(entityClass, entity.getId());

        return entityDao.update(entity);
    }

    @Override
    public List<Entity> findAll() {
        return entityDao.findAll();
    }

    @Override
    public Entity findById(Long id) {

        if (id == null) throw new BadArgumentException("Provided Id is null");

        Entity fromDb = entityDao.findById(id);
        if (fromDb == null) throw new MissingEntityException(entityClass, id);

        return entityDao.findById(id);
    }

    @Override
    public Long getCount() {
        return entityDao.count();
    }

    @Override
    public EntityDao<Entity> getEntityDao() {
        return entityDao;
    }
}
