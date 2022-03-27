package cz.fi.muni.pa165.movierecommender.dao;

import cz.fi.muni.pa165.movierecommender.entity.Movie;

import java.util.List;

/**
 * @author Maxim Svistunov
 */
public class MovieDaoImpl extends EntityDaoImpl<Movie> implements MovieDao {

    public MovieDaoImpl() {
        super(Movie.class);
    }

    @Override
    public List<Movie> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return em.createQuery("select entity from Movie entity where entity.name=:name", Movie.class)
                .setParameter("name", name)
                .getResultList();
    }
}
