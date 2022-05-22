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

    /**
     * Creates a movie entity. If this entity contains any actors or director (their ids),
     * they are modified as well and new movie (this one) is added to their collections
     * of acted in/directed movies.
     *
     * @param createDto entity's create DTO, not null
     * @return DTO of newly created movie
     */
    @Override
    MovieDto create(MovieCreateDto createDto);

    /**
     * Updates a movie entity. The behaviour is same asi with create() method.
     *
     * @param updateDto entity's update DTO, not null
     * @return DTO of newly updated movie
     */
    @Override
    MovieDto update(MovieUpdateDto updateDto);
}

