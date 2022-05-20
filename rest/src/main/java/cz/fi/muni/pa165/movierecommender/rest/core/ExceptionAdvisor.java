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

        HttpStatus httpStatus;
        if (exception instanceof BadArgumentException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof ForbiddenOperationException) {
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (exception instanceof LoginFailedException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof MissingEntityException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (exception instanceof EntityExistsException) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (exception instanceof EntityNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        log.debug("[{}] {} - {}", httpStatus, error.getName(), error.getMessage());

        return new ResponseEntity<>(error, httpStatus);
    }
}
