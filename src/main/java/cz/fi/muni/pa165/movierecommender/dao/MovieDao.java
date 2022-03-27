package cz.fi.muni.pa165.movierecommender.dao;

import cz.fi.muni.pa165.movierecommender.entity.Movie;

import java.util.List;

/**
 * @author Maxim Svistunov
 */
public interface MovieDao extends EntityDao<Movie> {

    /**
     * Find movies by name
     *
     * @param name name to search for
     * @return list of movies
     */
    List<Movie> findByName(String name);

}
