package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.entity.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public interface MovieService extends GenericService<Movie>{

    List<Movie> findByName(String name);
}
