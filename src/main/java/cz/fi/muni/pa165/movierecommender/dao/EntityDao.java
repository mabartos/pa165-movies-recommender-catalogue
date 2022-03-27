package cz.fi.muni.pa165.movierecommender.dao;

import cz.fi.muni.pa165.movierecommender.entity.GenericEntity;

import java.util.List;

/**
 * @author Daniel Puchala
 */
public interface EntityDao<Entity extends GenericEntity> {

    void create(Entity entity);

    void delete(Entity entity);

    void update(Entity entity);

    List<Entity> findAll();

    Entity findById(Long id);

    long count();
}
