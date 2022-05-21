package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Martin Bartoš
 */
@Service
public class MovieServiceImpl extends GenericServiceImpl<Movie> implements MovieService {

    private final MovieDao movieDao;
    private final RecommendationService recService;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, RecommendationService recService) {
        super(movieDao, Movie.class);
        this.movieDao = movieDao;
        this.recService = recService;
    }

    @Override
    public List<Movie> findByName(String name) {
        if (name == null) throw new BadArgumentException("Movie name is null");

        return movieDao.findByName(name);
    }

    @Override
    public List<Movie> getRecommendedByMovie(Long movieId) {
        if(movieId == null) throw new BadArgumentException("Movie id is null");
        final Movie movie = movieDao.findById(movieId);
        if(movie == null) throw new MissingEntityException(Movie.class,movieId);

        return recService.getRecommendedByMovie(movie);
    }
}
