package cz.fi.muni.pa165.movierecommender.service.service;


import cz.fi.muni.pa165.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;


import java.util.List;

/**
 * @author Daniel Puchala
 *
 * A common interface for all entity service
 * @param <Entity> of type GenericEntity
 */
public interface GenericService<Entity extends GenericEntity> {

    /**
     * Creates an entity.
     *
     * @param entity Entity to create
     * @throws BadArgumentException if provided entity is null
     */
    Entity create(Entity entity);

    /**
     * Deletes an entity
     *
     * @param entity Entity to be deleted
     * @throws BadArgumentException   if provided entity is null
     * @throws MissingEntityException if entity is not found
     */
    void delete(Entity entity);

    /**
     * Updates an entity.
     *
     * @param entity Entity to update
     * @throws BadArgumentException   if provided entity is null
     * @throws MissingEntityException if entity is not found
     */
    Entity update(Entity entity);

    /**
     * Finds all entity instances.
     *
     * @return all non-deleted entities
     */
    List<Entity> findAll();

    /**
     * Finds  entity for given ID
     *
     * @param id of searched entity
     * @return entity for given ID, never null
     * @throws BadArgumentException   if provided ID is null
     * @throws MissingEntityException if entity is not found
     */
    Entity findById(Long id);

    /**
     * Counts all entities of this type in database
     */
    Long getCount();

    /**
     * @return dao object of given entity
     */
    EntityDao<Entity> getEntityDao();

}
