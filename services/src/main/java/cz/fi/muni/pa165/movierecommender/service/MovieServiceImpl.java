package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.entity.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MovieServiceImpl extends GenericServiceImpl<Movie> implements MovieService{

    private final MovieDao movieDao;

    public MovieServiceImpl(EntityDao<Movie> entityDao, MovieDao movieDao) {
        super(entityDao);
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> findByName(String name) {
        return movieDao.findByName(name);
    }
}
