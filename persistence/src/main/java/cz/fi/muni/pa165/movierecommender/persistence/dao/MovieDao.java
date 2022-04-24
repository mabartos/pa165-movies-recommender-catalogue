package cz.fi.muni.pa165.movierecommender.persistence.dao;

import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Maxim Svistunov
 */
@Transactional
public interface MovieDao extends EntityDao<Movie> {

    /**
     * Find movies by name
     *
     * @param name Movie name to search for
     * @return list of movies
     * @throws IllegalArgumentException if name is null
     */
    List<Movie> findByName(String name);

    /**
     * Find movies by genre
     *
     * @param genre genre of movies
     * @return list of movies
     * @throws IllegalArgumentException if genre is null
     */
    List<Movie> findByGenre(Genre genre);

    /**
     * Find movies by director
     *
     * @param  director director of type Person whose movies we want to get
     * @return list of movies
     * @throws IllegalArgumentException if director is null
     */
    List<Movie> findByDirector(Person director);

    /**
     * Find movies by actor
     *
     * @param actor actor of type Person whose movies we want to get
     * @return list of movies
     * @throws IllegalArgumentException if name is null
     */
    List<Movie> findByActor(Person actor);
}
