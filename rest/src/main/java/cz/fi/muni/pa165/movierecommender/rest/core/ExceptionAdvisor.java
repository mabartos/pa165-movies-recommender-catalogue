package cz.fi.muni.pa165.movierecommender.rest.core;

import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.ForbiddenOperationException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.LoginFailedException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Inspired by example project
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler({
            Exception.class, // handle all exceptions
    })
    @ResponseBody
    public ResponseEntity<?> handleException(Exception exception) {
        ExceptionResponse error = new ExceptionResponse(exception.getClass().getSimpleName(), exception.getMessage());

        HttpStatus httpStatus = switch (exception) {
            case IllegalArgumentException ignored -> HttpStatus.BAD_REQUEST;
            case BadArgumentException ignored -> HttpStatus.BAD_REQUEST;
            case ForbiddenOperationException ignored -> HttpStatus.FORBIDDEN;
            case LoginFailedException ignored -> HttpStatus.UNAUTHORIZED;
            case MissingEntityException ignored -> HttpStatus.NOT_FOUND;
            case EntityExistsException ignored -> HttpStatus.UNPROCESSABLE_ENTITY;
            case EntityNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case AuthenticationException ignored -> HttpStatus.UNAUTHORIZED;
            case default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        log.debug("[{}] {} - {}", httpStatus, error.getName(), error.getMessage());

        return new ResponseEntity<>(error, httpStatus);
    }
}
