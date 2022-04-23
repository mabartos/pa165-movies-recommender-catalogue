package cz.fi.muni.pa165.movierecommender.service.service.exception;

/**
 * Exception that is thrown in Service layer if there is a problem with method's argument.
 *
 * Exception is mapped to HTTP response status of 400.
 */
public class BadArgumentException extends GeneralServiceException {

    public BadArgumentException(String message) {
        super(message);
    }

}
