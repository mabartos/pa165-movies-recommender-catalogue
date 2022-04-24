package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.create.CreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.UpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Petr Šlézar
 *
 * General abstract facade class. Note: each facade must implement its own version of service(), mapToEntity()
 * and merge with entity!
 *
 * @param <ENTITY> of type GenericEntity related to the facade
 * @param <CREATE_DTO> of type CreateDTO used for creating new entities
 * @param <UPDATE_DTO> of type UpdateDTO used for updating existing entities
 */
public abstract class GenericFacadeImpl
        <ENTITY extends GenericEntity,
        CREATE_DTO extends CreateDto,
        UPDATE_DTO extends UpdateDto
        >
        implements GenericFacade<CREATE_DTO, UPDATE_DTO> {

    @Override
    @Transactional
    public void create(CREATE_DTO createDto) {
        if (createDto == null) throw new IllegalArgumentException("Create DTO is null");

        ENTITY entity = mapToEntity(createDto);
        service().create(entity);
    }

    @Override
    @Transactional
    public void update(UPDATE_DTO updateDto) {
        if (updateDto == null) throw new IllegalArgumentException("Update DTO is null");

        ENTITY oldEntity = service().findById(updateDto.getId());
        ENTITY entityToUpdate = mergeWithEntity(updateDto, oldEntity);
        service().update(entityToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Id is null");

        ENTITY entity = service().findById(id);
        service().delete(entity);
    }

    /**
     * Obtain the main service of the facade
     *
     * @return Facade related service.
     */
    protected abstract GenericService<ENTITY> service();

    /**
     * Map DTO to entity in subclass to allow using specific classes required by DTO mapper.
     *
     * @param dto DTO that should be transformed into create-able entity
     * @return entity for creation
     */
    protected abstract ENTITY mapToEntity(CREATE_DTO dto);

    /**
     * Merge DTO with old entity in subclass to allow using specific classes required by DTO mapper.
     *
     * @param dto       DTO that should be transformed into update-able entity
     * @param oldEntity old version of entity that is being updated
     * @return entity for update
     */
    protected abstract ENTITY mergeWithEntity(UPDATE_DTO dto, ENTITY oldEntity);

}
