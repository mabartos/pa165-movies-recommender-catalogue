package dao;

import entity.GenericEntity;

import java.util.List;

/**
 * @author Daniel Puchala
 */
public interface EntityDao<Entity extends GenericEntity> {

    void create(Entity entity);

    void delete(Entity entity);

    List<Entity> findAll();

    Entity findById(Long id);

    long count();
}
