package cz.fi.muni.pa165.movierecommender.service.service.exception;

import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;

public class MissingEntityException extends GeneralServiceException {

    public MissingEntityException(Class<? extends GenericEntity> clazz, Long identifier) {
        super(String.format("{%s - %d} not found.", clazz.getSimpleName(), identifier));
    }

}
