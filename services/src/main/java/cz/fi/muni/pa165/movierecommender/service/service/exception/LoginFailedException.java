package cz.fi.muni.pa165.movierecommender.service.service.exception;


/**
 * Exception that is thrown in Api layer if there is a problem with login of system user.
 */
public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String message) {
        super(message);
    }

}
