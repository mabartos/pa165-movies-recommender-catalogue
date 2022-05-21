package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.create.CreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.UpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import cz.fi.muni.pa165.movierecommender.api.facade.GenericFacade;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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
                DTO,
                CREATE_DTO extends CreateDto,
                UPDATE_DTO extends UpdateDto
                >
        implements GenericFacade<DTO, CREATE_DTO, UPDATE_DTO> {

    @Override
    @Transactional
    public DTO findById(Long id) {

        final ENTITY entity = service().findById(id);
        return mapToDto(entity);
    }

    /**
     * Find all entities
     *
     * @return list of entity representation
     */
    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return service().findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DTO create(CREATE_DTO createDto) {

        ENTITY entity = mapToEntity(createDto);
        return mapToDto(service().create(entity));
    }

    @Override
    @Transactional
    public DTO update(UPDATE_DTO updateDto) {

        ENTITY entityToUpdate = mapToUpdatedEntity(updateDto);
        return mapToDto(service().update(entityToUpdate));
    }

    @Override
    @Transactional
    public void delete(Long id) {

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
     * Map entity to DTO in subclass to allow using specific classes required by entity mapper.
     *
     * @param entity entity that should be transformed into DTO
     * @return DTO
     */
    protected abstract DTO mapToDto(ENTITY entity);

    /**
     * Merge DTO with old entity in subclass to allow using specific classes required by DTO mapper.
     *
     * @param dto       DTO that should be transformed into update-able entity
     * @return entity for update
     */
    protected abstract ENTITY mapToUpdatedEntity(UPDATE_DTO dto);

}
