package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.entity.GenericEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class GenericServiceImpl<Entity extends GenericEntity> implements GenericService<Entity> {
    private final EntityDao<Entity> entityDao;

    public GenericServiceImpl(EntityDao<Entity> entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    public void create(Entity entity) {
        entityDao.create(entity);
    }

    @Override
    public void delete(Entity entity) {
        entityDao.delete(entity);
    }

    @Override
    public void update(Entity entity) {
        entityDao.update(entity);
    }

    @Override
    public List<Entity> findAll() {
        return entityDao.findAll();
    }

    @Override
    public Entity findById(Long id) {
        return entityDao.findById(id);
    }

    @Override
    public Long getCount() {
        return entityDao.count();
    }
}
