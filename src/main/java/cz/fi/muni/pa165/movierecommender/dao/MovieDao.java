package cz.fi.muni.pa165.movierecommender.dao;

import cz.fi.muni.pa165.movierecommender.entity.Movie;
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

}
