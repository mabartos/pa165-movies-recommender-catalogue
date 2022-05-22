package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.service.service.RecommendationService;
import cz.fi.muni.pa165.movierecommender.service.service.RecommendationServiceSimpleImpl;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashSet;
import java.util.List;

/**
 * @author Petr Šlézar
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieRecommendationServiceTest extends ServiceTestBase {

    RecommendationService service;
    @Mock
    private MovieDao movieDao;
    @Mock
    private PersonDao personDao;

    @Override
    protected void assignService() {
        service = new RecommendationServiceSimpleImpl(movieDao, personDao);
    }

    @Override
    protected void mockRepositoryMethods() {

        mockPersonRepositoryMethods();
        mockMovieRepositoryMethods();
    }

    private void mockPersonRepositoryMethods() {
        Mockito.when(personDao.findAll()).thenReturn(List.of(MockedEntities.QUENTIN, MockedEntities.TIM, MockedEntities.TERRY, MockedEntities.NON_EXISTENT_PERSON));

        Mockito.when(personDao.findById(MockedEntities.QUENTIN.getId())).thenReturn(MockedEntities.QUENTIN);
        Mockito.when(personDao.findById(MockedEntities.TIM.getId())).thenReturn(MockedEntities.TIM);
        Mockito.when(personDao.findById(MockedEntities.TERRY.getId())).thenReturn(MockedEntities.TERRY);

        Mockito.when(personDao.findByName("Quentin Tarantino")).thenReturn(List.of(MockedEntities.QUENTIN));
        Mockito.when(personDao.findByName("Tim Roth")).thenReturn(List.of(MockedEntities.TIM));
        Mockito.when(personDao.findByName("Terry Jones")).thenReturn(List.of(MockedEntities.TERRY));
        Mockito.when(personDao.findByName("Ja Veliky")).thenReturn(List.of(MockedEntities.NON_EXISTENT_PERSON));
    }

    //For testing of recommendation service, we also use NON_EXISTENT_MOVIE as it has no same Genre and no Director
    // - therefore should not be recommended
    private void mockMovieRepositoryMethods() {
        Mockito.when(movieDao.findAll()).thenReturn(List.of(MockedEntities.PULP_FICTION, MockedEntities.RESERVOIR_DOGS,
                MockedEntities.MONTY_PYTHON, MockedEntities.NON_EXISTENT_MOVIE));

        Mockito.when(movieDao.findById(MockedEntities.PULP_FICTION.getId())).thenReturn(MockedEntities.PULP_FICTION);
        Mockito.when(movieDao.findById(MockedEntities.RESERVOIR_DOGS.getId())).thenReturn(MockedEntities.RESERVOIR_DOGS);
        Mockito.when(movieDao.findById(MockedEntities.MONTY_PYTHON.getId())).thenReturn(MockedEntities.MONTY_PYTHON);
        Mockito.when(movieDao.findById(MockedEntities.NON_EXISTENT_MOVIE.getId())).thenReturn(MockedEntities.NON_EXISTENT_MOVIE);

        Mockito.when(movieDao.findByName(MockedEntities.PULP_FICTION.getName())).thenReturn(List.of(MockedEntities.PULP_FICTION));
        Mockito.when(movieDao.findByName(MockedEntities.RESERVOIR_DOGS.getName())).thenReturn(List.of(MockedEntities.RESERVOIR_DOGS));
        Mockito.when(movieDao.findByName(MockedEntities.MONTY_PYTHON.getName())).thenReturn(List.of(MockedEntities.MONTY_PYTHON));
        Mockito.when(movieDao.findByName(MockedEntities.NON_EXISTENT_MOVIE.getName())).thenReturn(List.of(MockedEntities.NON_EXISTENT_MOVIE));

        Mockito.when(movieDao.findByGenre(Genre.ACTION)).thenReturn(List.of(MockedEntities.PULP_FICTION, MockedEntities.RESERVOIR_DOGS, MockedEntities.MONTY_PYTHON));
        Mockito.when(movieDao.findByGenre(Genre.CRIME)).thenReturn(List.of(MockedEntities.PULP_FICTION, MockedEntities.RESERVOIR_DOGS));
        Mockito.when(movieDao.findByGenre(Genre.THRILLER)).thenReturn(List.of(MockedEntities.RESERVOIR_DOGS));
        Mockito.when(movieDao.findByGenre(Genre.COMEDY)).thenReturn(List.of(MockedEntities.MONTY_PYTHON));
        Mockito.when(movieDao.findByGenre(Genre.ADVENTURE)).thenReturn(List.of(MockedEntities.MONTY_PYTHON));
        Mockito.when(movieDao.findByGenre(Genre.FANTASY)).thenReturn(List.of(MockedEntities.MONTY_PYTHON));
        Mockito.when(movieDao.findByGenre(Genre.MYSTERY)).thenReturn(List.of(MockedEntities.NON_EXISTENT_MOVIE));

        Mockito.when(movieDao.findByDirector(MockedEntities.QUENTIN)).thenReturn(List.of(MockedEntities.PULP_FICTION, MockedEntities.RESERVOIR_DOGS, MockedEntities.MONTY_PYTHON));
        Mockito.when(movieDao.findByDirector(MockedEntities.TERRY)).thenReturn(List.of(MockedEntities.PULP_FICTION, MockedEntities.RESERVOIR_DOGS));
        Mockito.when(movieDao.findByDirector(MockedEntities.NON_EXISTENT_PERSON)).thenReturn(List.of(MockedEntities.NON_EXISTENT_MOVIE));

        //NO NEED TO MOCK .finyByActor() not used in recommendation (for now)
    }

    @Test
    public void recommendNullMovie() {
        Assertions.assertThatThrownBy(() -> service.getRecommendedByMovie(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void recommendForPulp() {

        List<Movie> recommendedForPulp = service.getRecommendedByMovie(MockedEntities.PULP_FICTION);
        Mockito.verify(movieDao, Mockito.times(1)).findByDirector(MockedEntities.PULP_FICTION.getDirector());
        for (Genre genre : MockedEntities.PULP_FICTION.getGenres()) {
            Mockito.verify(movieDao, Mockito.times(1)).findByGenre(genre);
        }
        Assertions.assertThat(recommendedForPulp).isNotNull();
        Assertions.assertThat(recommendedForPulp).hasSize(2);
        Assertions.assertThat(recommendedForPulp).containsExactlyInAnyOrder(MockedEntities.RESERVOIR_DOGS, MockedEntities.MONTY_PYTHON);
    }

    @Test
    public void recommendForReservoir() {

        List<Movie> recommendedForReservoir = service.getRecommendedByMovie(MockedEntities.RESERVOIR_DOGS);
        Mockito.verify(movieDao, Mockito.times(1)).findByDirector(MockedEntities.RESERVOIR_DOGS.getDirector());
        for (Genre genre : MockedEntities.RESERVOIR_DOGS.getGenres()) {
            Mockito.verify(movieDao, Mockito.times(1)).findByGenre(genre);
        }
        Assertions.assertThat(recommendedForReservoir).isNotNull();
        Assertions.assertThat(recommendedForReservoir).hasSize(2);
        Assertions.assertThat(recommendedForReservoir).containsExactlyInAnyOrder(MockedEntities.PULP_FICTION, MockedEntities.MONTY_PYTHON);
    }

    @Test
    public void recommendedForMontyPython() {

        List<Movie> recommendedForMonty = service.getRecommendedByMovie(MockedEntities.MONTY_PYTHON);
        Mockito.verify(movieDao, Mockito.times(1)).findByDirector(MockedEntities.MONTY_PYTHON.getDirector());
        for (Genre genre : MockedEntities.MONTY_PYTHON.getGenres()) {
            Mockito.verify(movieDao, Mockito.times(1)).findByGenre(genre);
        }
        Assertions.assertThat(recommendedForMonty).isNotNull();
        Assertions.assertThat(recommendedForMonty).hasSize(2);
        Assertions.assertThat(recommendedForMonty).containsExactlyInAnyOrder(MockedEntities.PULP_FICTION, MockedEntities.RESERVOIR_DOGS);
    }

    @Test
    public void recommendedForNonExistent() {

        List<Movie> recommendedForNonExistent = service.getRecommendedByMovie(MockedEntities.NON_EXISTENT_MOVIE);
        Mockito.verify(movieDao, Mockito.times(1)).findByDirector(MockedEntities.NON_EXISTENT_MOVIE.getDirector());
        for (Genre genre : MockedEntities.NON_EXISTENT_MOVIE.getGenres()) {
            Mockito.verify(movieDao, Mockito.times(1)).findByGenre(genre);
        }
        Assertions.assertThat(recommendedForNonExistent).isNotNull();
        Assertions.assertThat(recommendedForNonExistent).hasSize(0);
    }

    @Test
    public void recommendedForNewSharingOnlyDirector() {

        Movie newQuentinMovie = new Movie(MockedEntities.QUENTIN,"New Historical Tarantino", 120, "Poster", new HashSet<Genre>(List.of(Genre.HISTORY)), "desc", 1234, null, null);
        newQuentinMovie.setId(10L);
        Mockito.when(movieDao.findById(newQuentinMovie.getId())).thenReturn(newQuentinMovie);
        Mockito.when(movieDao.findByName(newQuentinMovie.getName())).thenReturn(List.of(newQuentinMovie));
        Mockito.when(movieDao.findByGenre(Genre.HISTORY)).thenReturn(List.of(newQuentinMovie));
        Mockito.when(movieDao.findByDirector(MockedEntities.QUENTIN)).thenReturn(List.of(newQuentinMovie, MockedEntities.RESERVOIR_DOGS, MockedEntities.PULP_FICTION));

        List<Movie> recommendedForNew = service.getRecommendedByMovie(newQuentinMovie);
        Mockito.verify(movieDao, Mockito.times(1)).findByDirector(newQuentinMovie.getDirector());
        for (Genre genre : newQuentinMovie.getGenres()) {
            Mockito.verify(movieDao, Mockito.times(1)).findByGenre(genre);
        }
        Assertions.assertThat(recommendedForNew).isNotNull();
        Assertions.assertThat(recommendedForNew).hasSize(2);
        Assertions.assertThat(recommendedForNew).containsExactlyInAnyOrder(MockedEntities.PULP_FICTION, MockedEntities.RESERVOIR_DOGS);
    }

}
