package cz.fi.muni.pa165.movierecommender.service.service.exception;

/**
 * Exception that is thrown in Service layer if there is a problem with method's argument.
 *
 */
public class BadArgumentException extends GeneralServiceException {

    public BadArgumentException(String message) {
        super(message);
    }

}
