package cz.fi.muni.pa165.movierecommender.persistence.dao;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Maxim Svistunov
 */
@Repository
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

    @Override
    public List<Movie> findByGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }
        return em.createQuery("select entity from Movie entity join entity.genres genres where :genre_param MEMBER OF genres", Movie.class)
                .setParameter("genre_param", genre)
                .getResultList();
    }

    @Override
    public List<Movie> findByDirector(Person director) {
        if (director == null) {
            throw new IllegalArgumentException("Director cannot be null");
        }
        return em.createQuery("select entity from Movie entity where entity.director =:director", Movie.class)
                .setParameter("director", director)
                .getResultList();
    }

    @Override
    public List<Movie> findByActor(Person actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }

        return em.createQuery("select entity from Movie entity join entity.actors actors where :actor_param MEMBER OF actors", Movie.class)
                .setParameter("actor_param", actor)
                .getResultList();
    }
}
