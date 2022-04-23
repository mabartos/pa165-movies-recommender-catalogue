package cz.fi.muni.pa165.movierecommender.service.service.exception;

/**
 * Service exceptions superclass.
 */
public abstract class GeneralServiceException extends RuntimeException {
    public GeneralServiceException(String message) {
        super(message);
    }
}
