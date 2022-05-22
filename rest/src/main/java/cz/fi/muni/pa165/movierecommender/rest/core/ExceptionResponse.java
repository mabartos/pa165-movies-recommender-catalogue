package cz.fi.muni.pa165.movierecommender.rest.core;

import lombok.Getter;

/**
 * Inspired by example project's ErrorResource
 * @author Petr Šlézar
 */
@Getter
public class ExceptionResponse {
    private final String name;
    private final String message;

    public ExceptionResponse(String name, String message) {
        this.name = name;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "code='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
