package cz.fi.muni.pa165.movierecommender.dao;

import cz.fi.muni.pa165.movierecommender.entity.Movie;
import cz.fi.muni.pa165.movierecommender.entity.Review;
import cz.fi.muni.pa165.movierecommender.entity.User;

import java.util.List;

/**
 * @author Petr Slezar
 */
public interface ReviewDao extends EntityDao<Review>{

    /**
     * Find all reviews created by a specific 'user'
     *
     * @param user required movie which review is to be found
     * @return list of reviews
     */
    List<Review> findByUser(User user);

    /**
     * Find all reviews related to a given 'movie'
     *
     * @param movie required movie which review is to be found
     * @return list of reviews
     */
    List<Review> findByMovie(Movie movie);

    /**
     * Find a review related to a given 'movie' and created by a specific 'user'
     *
     * @param movie required movie which review is to be found
     * @param user required user which review is to be found
     * @return a single review
     */
    Review findByMovieAndUser(Movie movie, User user);
}
