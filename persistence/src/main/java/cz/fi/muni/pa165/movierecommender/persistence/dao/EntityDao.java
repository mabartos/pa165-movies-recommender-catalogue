package cz.fi.muni.pa165.movierecommender.persistence.dao;

import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Daniel Puchala
 */
@Transactional
public interface EntityDao<Entity extends GenericEntity> {

    /**
     * Creates an entity.
     *
     * @param entity Entity to create
     * @throws IllegalArgumentException if provided entity is null
     * @throws javax.persistence.EntityExistsException if entity with same ID already exists
     */
    void create(Entity entity);

    //Still needs to be improved (cascading delete etc.)
    /**
     * Deletes an entity in a database
     *
     * @param entity Entity to be deleted
     * @throws IllegalArgumentException    if provided entity is null
     * @throws javax.persistence.EntityNotFoundException if there is no pre-existing entity that should be deleted
     */
    void delete(Entity entity);

    /**
     * Updates an entity.
     *
     * @param entity Entity to update
     * @throws IllegalArgumentException if provided entity is null
     * @throws javax.persistence.EntityNotFoundException if there is no pre-existing entity that should be updated
     */
    void update(Entity entity);

    /**
     * Finds all entity instances.
     *
     * @return all entities
     */
    List<Entity> findAll();

    /**
     * Finds entity for given ID
     *
     * @param id Id of searched entity
     * @return Null if entity is not found, entity otherwise
     * @throws IllegalArgumentException if provided ID is null
     */
    Entity findById(Long id);

    /**
     * Counts all entities
     *
     * @return Number of entities
     */
    Long count();
}
