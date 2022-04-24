package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecommendationServiceSimpleImpl implements RecommendationService{

    private final MovieDao movieDao;
    private final PersonDao personDao;

    @Autowired
    public RecommendationServiceSimpleImpl(MovieDao movieDao, PersonDao personDao) {
        this.movieDao = movieDao;
        this.personDao = personDao;
    }

    @Override
    public List<Movie> getRecommendedByMovie(Movie movie) {

        if(movie == null) throw new BadArgumentException("Movie is null");

        //We use Set that is later converted to list to avoid duplicates
        Set<Movie> recommendedMovies = new HashSet<>();

        List<Movie> byGenres = getRecommendedByGenres(movie.getGenres());
        List<Movie> byDirector = getRecommendedByDirector(movie.getDirector().getName());

        recommendedMovies.addAll(byDirector);
        recommendedMovies.addAll(byGenres);

        return new ArrayList<>(recommendedMovies);
    }

    private List<Movie> getRecommendedByGenres(Set<Genre> genres){

        List<Movie> allFound = new ArrayList<>();

        for (Genre genre: genres) {

            allFound.addAll(movieDao.findByGenre(genre));
        }

        return allFound;
    }

    /*
     We are passing name as the Person object form movie might be incomplete (the movie detail caries simplified person
     info)
     */
    private List<Movie> getRecommendedByDirector(String directorName){

        List<Movie> recommendedByDirector = new ArrayList<>();

        List<Person> directorList = personDao.findByName(directorName);
        if(directorList.isEmpty()) return recommendedByDirector;

        //We assume passing full director name from film detail page, therefore exact match for first element
        Person director = directorList.get(0);

        return movieDao.findByDirector(director);
    }
}
