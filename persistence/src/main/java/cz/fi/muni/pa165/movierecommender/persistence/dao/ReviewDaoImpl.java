package cz.fi.muni.pa165.movierecommender.persistence.dao;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link ReviewDao} interface.
 *
 * @author Petr Slezar
 */
@Repository
public class ReviewDaoImpl extends EntityDaoImpl<Review> implements ReviewDao {

    public ReviewDaoImpl() {
        super(Review.class);
    }

    @Override
    public List<Review> findByUser(User user) throws IllegalArgumentException {
        if (user == null){
            throw new IllegalArgumentException("User is null");
        }
        return em.createQuery("select entity from Review entity where entity.user=:user", Review.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Review> findByMovie(Movie movie) throws IllegalArgumentException {
        if (movie == null){
            throw new IllegalArgumentException("Movie is null");
        }
        return em.createQuery("select entity from Review entity where entity.movie=:movie", Review.class)
                .setParameter("movie", movie)
                .getResultList();
    }

    @Override
    public Review findByMovieAndUser(Movie movie, User user) throws IllegalArgumentException {
        if (movie == null || user == null) throw new IllegalArgumentException("At least on argument is null");
        List<Review> entity = em.createQuery("select entity from Review entity where entity.movie=:movie " +
                        "and entity.user=:user ", Review.class)
                .setParameter("movie", movie)
                .setParameter("user", user)
                .getResultList();
        if (entity.isEmpty()) return null;
        else return entity.get(0);
    }

}