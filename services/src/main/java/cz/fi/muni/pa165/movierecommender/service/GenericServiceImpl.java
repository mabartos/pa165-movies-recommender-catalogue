package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;


import java.util.List;

public abstract class GenericServiceImpl<Entity extends GenericEntity> implements GenericService<Entity> {

    @Override
    public void create(Entity entity) {
        if (entity == null) throw new IllegalArgumentException("Provided Entity is null");

        getEntityDao().create(entity);
    }

    @Override
    public void delete(Entity entity) {
        if (entity == null) throw new IllegalArgumentException("Provided Entity is null");

        getEntityDao().delete(entity);
    }

    @Override
    public void update(Entity entity) {
        if (entity == null) throw new IllegalArgumentException("Provided Entity is null");

        getEntityDao().update(entity);
    }

    @Override
    public List<Entity> findAll() {
        return getEntityDao().findAll();
    }

    @Override
    public Entity findById(Long id) {
        return getEntityDao().findById(id);
    }

    @Override
    public Long getCount() {
        return getEntityDao().count();
    }

    public abstract EntityDao<Entity> getEntityDao();
}
