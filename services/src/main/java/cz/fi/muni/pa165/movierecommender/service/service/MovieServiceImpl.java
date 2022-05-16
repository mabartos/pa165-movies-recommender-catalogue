package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return movieDao.findByName(name);
    }

    @Override
    public List<Movie> getRecommendedByMovie(Long movieId) {
        final Movie movie = movieDao.findById(movieId);
        return recService.getRecommendedByMovie(movie);
    }
}
