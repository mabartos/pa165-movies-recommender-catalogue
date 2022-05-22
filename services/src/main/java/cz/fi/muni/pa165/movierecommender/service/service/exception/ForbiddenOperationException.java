package cz.fi.muni.pa165.movierecommender.service.service.exception;

/**
 * Exception that is thrown in Service layer if operation is forbidden.
 */
public class ForbiddenOperationException extends GeneralServiceException {

    public ForbiddenOperationException(String message) {
        super(message);
    }

}
