package cz.fi.muni.pa165.movierecommender.rest.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidTokenException(String msg) {
        super(msg);
    }

    public InvalidTokenException() {
        super("Invalid token");
    }
}
