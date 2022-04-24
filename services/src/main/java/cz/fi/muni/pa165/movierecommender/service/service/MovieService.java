package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;

import java.util.List;

public interface MovieService extends GenericService<Movie> {

    List<Movie> findByName(String name);
}
