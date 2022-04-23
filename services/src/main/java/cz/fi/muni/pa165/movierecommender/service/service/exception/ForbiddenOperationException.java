package cz.fi.muni.pa165.movierecommender.service.service.exception;

/**
 * Exception that is thrown in Service layer if operation is forbidden.
 *
 * Exception is mapped to HTTP response status of 403.
 */
public class ForbiddenOperationException extends GeneralServiceException {

    public ForbiddenOperationException(String message) {
        super(message);
    }

}
