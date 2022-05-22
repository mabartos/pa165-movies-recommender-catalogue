package cz.fi.muni.pa165.movierecommender.rest.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Inspired by https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/
 * @author Petr Šlézar
 */
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
