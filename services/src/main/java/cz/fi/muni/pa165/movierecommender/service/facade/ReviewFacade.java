package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.ReviewUpdateDto;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;

import java.util.List;

/**
 * @author Petr Šlézar
 *
 * A facade used for operations for review.
 *
 */
public interface ReviewFacade extends GenericFacade<ReviewCreateDto, ReviewUpdateDto>{

    /**
     * Find all reviews created by a specific 'user'
     *
     * @param id of review to be found
     * @return list of reviews
     * @throws BadArgumentException when id parameter is null
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
     * @throws BadArgumentException when user parameter is null
     */
    List<ReviewDto> findByUser(Long userId);

    /**
     * Find all reviews related to a given 'movie'
     *
     * @param movieId of movie id which reviews are to be found
     * @return list of reviews
     * @throws BadArgumentException when movie parameter is null
     */
    List<ReviewDto> findByMovie(Long movieId);

    /**
     * Find a review related to a given 'movie' and created by a specific 'user'
     *
     * @param movieId required movie id which review is to be found
     * @param userId required user id which review is to be found
     * @return a single review
     * @throws BadArgumentException when either user or movie parameter is null
     */
    ReviewDto findByMovieAndUser(Long movieId, Long userId);
}
