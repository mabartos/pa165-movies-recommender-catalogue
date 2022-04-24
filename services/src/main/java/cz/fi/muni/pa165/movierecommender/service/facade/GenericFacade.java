package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.create.CreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.UpdateDto;

/**
 * @author Petr Šlézar
 *
 * A generic interface for facade common to all facades
 *
 * @param <CREATE_DTO> which extends class implementing CreateDTO
 * @param <UPDATE_DTO> which extends class implementing UpdateDTO
 */
public interface GenericFacade<
        CREATE_DTO extends CreateDto,
        UPDATE_DTO extends UpdateDto
        > {

    /**
     * Creates an entity.
     *
     * @param createDto entity's create DTO, not null
     * @throws IllegalArgumentException if createDto is null
     */
    void create(CREATE_DTO createDto);

    /**
     * Updates an entity.
     *
     * @param updateDto entity's update DTO, not null
     * @throws IllegalArgumentException if updateDto is null
     */
    void update(UPDATE_DTO updateDto);

    /**
     * Deletes entity with given id.
     *
     * @param id of the entity for deletion, not null
     * @throws IllegalArgumentException if ID is null
     */
    void delete(Long id);
}