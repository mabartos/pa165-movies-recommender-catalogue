package cz.fi.muni.pa165.movierecommender.api.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;

import java.util.List;

/**
 * @author Maxim Svistunov
 * <p>
 * A facade used for movie.
 */
public interface MovieFacade extends GenericFacade<MovieDto, MovieCreateDto, MovieUpdateDto> {

    /**
     * Find movies that contain a substring in their names.
     *
     * @param name substring in a name
     * @return list of movies
     */
    List<MovieDto> findByName(String name);

    List<MovieDto> getRecommendedByMovie(Long movieId);
}

