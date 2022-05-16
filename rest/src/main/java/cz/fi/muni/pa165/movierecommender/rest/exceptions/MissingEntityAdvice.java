package cz.fi.muni.pa165.movierecommender.rest.exceptions;

import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MissingEntityAdvice {

    @ResponseBody
    @ExceptionHandler(MissingEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String missingEntity(MissingEntityException ex) {
        return ex.getMessage();
    }
}
