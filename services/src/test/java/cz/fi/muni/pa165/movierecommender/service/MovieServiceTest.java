package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.service.service.MovieService;
import cz.fi.muni.pa165.movierecommender.service.service.MovieServiceImpl;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Svistunov
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieServiceTest extends ServiceTestBase {
    @Mock
    private MovieDao dao;

    MovieService service;

    Movie movie1 = MockedEntities.RESERVOIR_DOGS;
    Movie movie2 = MockedEntities.MONTY_PYTHON;
    Movie movie3 = MockedEntities.PULP_FICTION;
    Movie nonExistingMovie = MockedEntities.NON_EXISTENT_MOVIE;

    @Override
    protected void assignService() {
        service = new MovieServiceImpl(dao);
    }

    @Override
    protected void mockRepositoryMethods() {
        List<Movie> allMovies = List.of(movie1, movie2, movie3);
        when(dao.findAll()).thenReturn(allMovies);

        for (Movie movie: allMovies) {
            when(dao.findById(movie.getId())).thenReturn(movie);
            when(dao.findByName(movie.getName())).thenReturn(List.of(movie));
        }
    }

    @Test
    public void findAll() {
        List<Movie> movies = service.findAll();
        assertThat(movies).isNotNull();
        assertThat(movies).containsExactlyInAnyOrder(movie1, movie2, movie3);
    }

    @Test
    public void findByName() {
        List<Movie> movies = service.findByName(movie1.getName());
        assertThat(movies).isNotNull();
        assertThat(movies).containsExactlyInAnyOrder(movie1);
    }

    @Test
    public void create() {
        service.create(nonExistingMovie);
        verify(dao, Mockito.times(1)).create(nonExistingMovie);
    }

    @Test
    public void createNull() {
        Assertions.assertThatThrownBy(() -> service.create(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void update() {
        Movie entityToUpdate = service.findById(movie1.getId());
        entityToUpdate.setName("different name");
        service.update(entityToUpdate);
        verify(dao, Mockito.times(1)).update(entityToUpdate);
    }

    @Test
    public void updateNull() {
        Assertions.assertThatThrownBy(() -> service.update(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findByIdNull() {
        Assertions.assertThatThrownBy(() -> service.findById(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void delete() {
        service.delete(movie1);
        Mockito.verify(dao, Mockito.times(1)).delete(movie1);
    }

    @Test
    public void deleteNull() {
        Assertions.assertThatThrownBy(() -> service.delete(null)).isInstanceOf(BadArgumentException.class);
    }
}
