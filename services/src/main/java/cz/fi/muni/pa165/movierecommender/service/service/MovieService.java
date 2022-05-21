package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;

import java.util.List;

/**
 * @author Maxim Svistunov
 * <p>
 * A service used for a movie.
 */
public interface MovieService extends GenericService<Movie> {

    /**
     * Find all movies by a specific name
     *
     * @param name of a movie corresponding potentially to more movies
     * @return list of movies
     * @throws BadArgumentException when name parameter is null
     */
    List<Movie> findByName(String name);

    /**
     * Find all movies by a specific name
     *
     * @param movieId of a movie for which we search for recommendation
     * @return list of movies recommended by the provided movie identifier
     * @throws BadArgumentException when movie id is null
     * @throws MissingEntityException if movie entity is not found
     */
    List<Movie> getRecommendedByMovie(Long movieId);
}
