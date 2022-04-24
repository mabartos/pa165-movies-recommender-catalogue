package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.ReviewDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
