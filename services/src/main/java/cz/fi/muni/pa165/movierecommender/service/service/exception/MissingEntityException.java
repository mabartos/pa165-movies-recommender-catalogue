package cz.fi.muni.pa165.movierecommender.service.service.exception;

import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;

/**
 * Exception that is thrown in Service layer if when the entity does not exist.
 */
public class MissingEntityException extends GeneralServiceException {

    public MissingEntityException(Class<? extends GenericEntity> clazz, Long identifier) {
        super(String.format("{%s - %d} not found.", clazz.getSimpleName(), identifier));
    }

    public MissingEntityException(Class<? extends GenericEntity> clazz, String name) {
        super(String.format("{%s - name: %s} not found.", clazz.getSimpleName(), name));
    }

}
