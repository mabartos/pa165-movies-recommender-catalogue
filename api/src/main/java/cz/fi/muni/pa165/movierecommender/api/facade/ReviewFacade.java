package cz.fi.muni.pa165.movierecommender.api.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.ReviewUpdateDto;

import java.util.List;

/**
 * @author Petr Šlézar
 * <p>
 * A facade used for operations for review.
 */
public interface ReviewFacade extends GenericFacade<ReviewDto, ReviewCreateDto, ReviewUpdateDto> {

    /**
     * Find all reviews created by a specific 'user'
     *
     * @param id of review to be found
     * @return list of reviews
     */
    ReviewDto findById(Long id);

    /**
     * Find all reviews related to a given 'movie'
     *
     * @return list of all reviews
     */
    List<ReviewDto> findAll();
    /**
     * Find all reviews created by a specific 'user'
     *
     * @param userId of user which reviews are to be found
     * @return list of reviews
     */
    List<ReviewDto> findByUser(Long userId);

    /**
     * Find all reviews related to a given 'movie'
     *
     * @param movieId of movie id which reviews are to be found
     * @return list of reviews
     */
    List<ReviewDto> findByMovie(Long movieId);

    /**
     * Find a review related to a given 'movie' and created by a specific 'user'
     *
     * @param movieId required movie id which review is to be found
     * @param userId required user id which review is to be found
     * @return a single review
     */
    ReviewDto findByMovieAndUser(Long movieId, Long userId);

    /**
     * Computes an average rating of a movie from its reviews
     *
     * @param movieId required movie ID which average rating is to be computed
     * @return a double number rounded to 2 decimal points
     */
    Double getAverageRating(Long movieId);
}
