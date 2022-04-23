package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service interface for providing a movie recommendations. Now it is quite simple but can be further improved.
 *
 * @author Petr Šlézar | xslezar@mail.muni.cz
 */
@Service
public interface RecommendationService {

    /**
     * Recommends movies based on similarity of director and genre
     * @param movie for which to find similar movies for recommendation
     * @return a list of similar movies that are recommended
     * @throws BadArgumentException if movie is null
     */
    List<Movie> getRecommendedByMovie(Movie movie);
}
