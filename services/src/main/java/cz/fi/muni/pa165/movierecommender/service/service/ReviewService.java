package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;

import java.util.List;

@Transactional
@Service
public interface ReviewService extends GenericService<Review>{


    /**
     * Find all reviews created by a specific 'user'
     *
     * @param user required movie which review is to be found
     * @return list of reviews
     * @throws BadArgumentException when user parameter is null
     */
    List<Review> findByUser(User user);

    /**
     * Find all reviews related to a given 'movie'
     *
     * @param movie required movie which review is to be found
     * @return list of reviews
     * @throws BadArgumentException when movie parameter is null
     */
    List<Review> findByMovie(Movie movie);

    /**
     * Find a review related to a given 'movie' and created by a specific 'user'
     *
     * @param movie required movie which review is to be found
     * @param user required user which review is to be found
     * @return a single review
     * @throws BadArgumentException when either user or movie parameter is null
     */
    Review findByMovieAndUser(Movie movie, User user);
}