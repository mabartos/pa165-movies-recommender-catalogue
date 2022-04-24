package cz.fi.muni.pa165.movierecommender.api.facade;

import java.util.List;

public interface EntityFacade<Entity> {

    /**
     * Creates an entity.
     *
     * @param entity Entity to create
     * @throws IllegalArgumentException if provided entity is null
     */
    void create(Entity entity);

    //Still needs to be improved (cascading delete etc.)
    /**
     * Deletes an entity in a database
     *
     * @param entity Entity to be deleted
     * @throws IllegalArgumentException    if provided entity is null
     */
    void delete(Entity entity);

    /**
     * Updates an entity.
     *
     * @param entity Entity to update
     * @throws IllegalArgumentException if provided entity is null
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
     */
    Entity findById(Long id);

    /**
     * Counts all entities
     *
     * @return Number of entities
     */
    Long count();

}
