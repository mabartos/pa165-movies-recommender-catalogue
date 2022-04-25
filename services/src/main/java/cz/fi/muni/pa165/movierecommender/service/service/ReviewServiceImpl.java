package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.ReviewDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.util.Precision;

import java.util.List;

/**
 * @author Petr Šlézar
 *
 */
@Service
public class ReviewServiceImpl extends GenericServiceImpl<Review> implements ReviewService{

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        super(reviewDao, Review.class);
        this.reviewDao = reviewDao;
    }

    @Override
    public void create(Review entity) {
        if (entity == null) {
            throw new BadArgumentException("Provided Entity is null");
        }
        Review existingReview= reviewDao.findByMovieAndUser(entity.getMovie(),entity.getUser());
        if(existingReview != null)
            throw new BadArgumentException("A user with id " + entity.getUser().getId() + "cannot have more reviews for movie " + entity.getMovie().getName() );

        reviewDao.create(entity);
    }

    @Override
    public void update(Review entity) {
        if (entity == null) throw new BadArgumentException("Provided Entity is null");

        Review existingReview= reviewDao.findByMovieAndUser(entity.getMovie(),entity.getUser());
        if(existingReview == null)
            throw new MissingEntityException(Review.class,entity.getId());

        reviewDao.update(entity);
    }

    @Override
    public List<Review> findByUser(User user) {
        if(user == null) throw new BadArgumentException("User is null");

        return reviewDao.findByUser(user);
    }

    @Override
    public List<Review> findByMovie(Movie movie) {
        if(movie == null) throw new BadArgumentException("Movie is null");

        return reviewDao.findByMovie(movie);
    }

    //can return null
    @Override
    public Review findByMovieAndUser(Movie movie, User user) {

        if(movie == null || user == null) throw new BadArgumentException("Movie or user is null");

        return reviewDao.findByMovieAndUser(movie,user);
    }

    //can return null
    @Override
    public Double getAverageRating (Movie movie) {
        if(movie == null) throw new BadArgumentException("Movie is null");

        List<Review> allReviewsForMovie = findByMovie(movie);
        Double totalScore = Double.valueOf(
                allReviewsForMovie.stream().map( review -> review.getActingRating() +
                review.getIdeaRating() +
                review.getMusicRating() +
                review.getScriptRating() +
                review.getVisualsEditRating()).reduce(0, Integer::sum)
        );
        Double divisor = Double.valueOf(allReviewsForMovie.size() * 5);
        Double averageRating = 0d;
        if(totalScore != 0){
            averageRating = totalScore / divisor;
            averageRating = Precision.round(averageRating,2);
        }

        return averageRating;
    }
}
