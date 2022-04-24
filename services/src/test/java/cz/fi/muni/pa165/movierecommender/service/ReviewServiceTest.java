package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.ReviewDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.service.service.ReviewService;
import cz.fi.muni.pa165.movierecommender.service.service.ReviewServiceImpl;

import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

/**
 * @author Petr Šlézar
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReviewServiceTest extends ServiceTestBase {

    @Mock
    private ReviewDao dao;

    ReviewService service;

    @Override
    protected void assignService() {
        service = new ReviewServiceImpl(dao);
    }

    @Override
    protected void mockRepositoryMethods() {
        Mockito.when(dao.findAll()).thenReturn(List.of(MockedEntities.PEPA_MONTY_REVIEW, MockedEntities.KAREL_PULP_REVIEW,MockedEntities.HONZA_RESERVOIR_REVIEW,MockedEntities.PEPA_RESERVOIR_REVIEW));

        Mockito.when(dao.findById(MockedEntities.PEPA_RESERVOIR_REVIEW.getId())).thenReturn(MockedEntities.PEPA_RESERVOIR_REVIEW);
        Mockito.when(dao.findById(MockedEntities.HONZA_RESERVOIR_REVIEW.getId())).thenReturn(MockedEntities.HONZA_RESERVOIR_REVIEW);
        Mockito.when(dao.findById(MockedEntities.KAREL_PULP_REVIEW.getId())).thenReturn(MockedEntities.KAREL_PULP_REVIEW);
        Mockito.when(dao.findById(MockedEntities.PEPA_MONTY_REVIEW.getId())).thenReturn(MockedEntities.PEPA_MONTY_REVIEW);

        Mockito.when(dao.findByMovie(MockedEntities.PULP_FICTION)).thenReturn(List.of(MockedEntities.KAREL_PULP_REVIEW));
        Mockito.when(dao.findByMovie(MockedEntities.MONTY_PYTHON)).thenReturn(List.of(MockedEntities.PEPA_MONTY_REVIEW));
        Mockito.when(dao.findByMovie(MockedEntities.RESERVOIR_DOGS)).thenReturn(List.of(MockedEntities.PEPA_RESERVOIR_REVIEW,MockedEntities.HONZA_RESERVOIR_REVIEW));

        Mockito.when(dao.findByUser(MockedEntities.HONZA)).thenReturn(List.of(MockedEntities.HONZA_RESERVOIR_REVIEW));
        Mockito.when(dao.findByUser(MockedEntities.PEPA)).thenReturn(List.of(MockedEntities.PEPA_RESERVOIR_REVIEW,MockedEntities.PEPA_MONTY_REVIEW));
        Mockito.when(dao.findByUser(MockedEntities.KAREL)).thenReturn(List.of(MockedEntities.KAREL_PULP_REVIEW));

        Mockito.when(dao.findByMovieAndUser(MockedEntities.RESERVOIR_DOGS,MockedEntities.PEPA)).thenReturn(MockedEntities.PEPA_RESERVOIR_REVIEW);
        Mockito.when(dao.findByMovieAndUser(MockedEntities.RESERVOIR_DOGS,MockedEntities.HONZA)).thenReturn(MockedEntities.HONZA_RESERVOIR_REVIEW);
        Mockito.when(dao.findByMovieAndUser(MockedEntities.PULP_FICTION,MockedEntities.KAREL)).thenReturn(MockedEntities.KAREL_PULP_REVIEW);
        Mockito.when(dao.findByMovieAndUser(MockedEntities.MONTY_PYTHON,MockedEntities.PEPA)).thenReturn(MockedEntities.PEPA_MONTY_REVIEW);

    }

    @Test
    public void create() {
        service.create(MockedEntities.NON_EXISTENT_REVIEW);
        Mockito.verify(dao, Mockito.times(1)).findByMovieAndUser(MockedEntities.NON_EXISTENT_MOVIE, MockedEntities.NON_EXISTENT_USER);
        Mockito.verify(dao, Mockito.times(1)).create(MockedEntities.NON_EXISTENT_REVIEW);
    }

    @Test
    public void createNull() {
        Assertions.assertThatThrownBy(() -> service.create(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void createExistingReview() {
        Assertions.assertThatThrownBy(() -> service.create(MockedEntities.HONZA_RESERVOIR_REVIEW)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void update() {
        Review entityToUpdate = new Review(MockedEntities.KAREL_PULP_REVIEW.getUser(),MockedEntities.KAREL_PULP_REVIEW.getMovie(),"I changed my mind",10,10,10,10,10);
        entityToUpdate.setId(MockedEntities.KAREL_PULP_REVIEW.getId());
        service.update(entityToUpdate);
        Mockito.verify(dao, Mockito.times(1)).update(entityToUpdate);

        List<Review> reviews = service.findByMovie(MockedEntities.PULP_FICTION);
        Review review = reviews.get(0);
        review.setActingRating(7);
        service.update(review);
        Assertions.assertThat(review.getActingRating()).isEqualTo(7);
    }

    @Test
    public void updateNull() {
        Assertions.assertThatThrownBy(() -> service.update(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void updateNonExistent() {
        Assertions.assertThatThrownBy(() -> service.update(MockedEntities.NON_EXISTENT_REVIEW)).isInstanceOf(MissingEntityException.class);
    }

    @Test
    public void findById() {
        Review fromRepository = service.findById(MockedEntities.PEPA_MONTY_REVIEW.getId());
        Assertions.assertThat(fromRepository).isNotNull();
        Assertions.assertThat(fromRepository).usingRecursiveComparison().isEqualTo(MockedEntities.PEPA_MONTY_REVIEW);
    }

    @Test
    public void findByIdNull() {
        Assertions.assertThatThrownBy(() -> service.findById(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findByUser() {
        List<Review> fromRepository = service.findByUser(MockedEntities.PEPA);
        Assertions.assertThat(fromRepository).isNotNull();
        Assertions.assertThat(fromRepository).hasSize(2);
        Assertions.assertThat(fromRepository).containsExactlyInAnyOrder(MockedEntities.PEPA_MONTY_REVIEW,MockedEntities.PEPA_RESERVOIR_REVIEW);
    }

    @Test
    public void findByUserNull() {
        Assertions.assertThatThrownBy(() -> service.findByUser(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findByMovie() {
        List<Review> fromRepository = service.findByMovie(MockedEntities.RESERVOIR_DOGS);
        Assertions.assertThat(fromRepository).isNotNull();
        Assertions.assertThat(fromRepository).hasSize(2);
        Assertions.assertThat(fromRepository).containsExactlyInAnyOrder(MockedEntities.PEPA_RESERVOIR_REVIEW, MockedEntities.HONZA_RESERVOIR_REVIEW);
    }

    @Test
    public void findByMovieNull() {
        Assertions.assertThatThrownBy(() -> service.findByMovie(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findByMovieAndUser() {
        Review fromRepository = service.findByMovieAndUser(MockedEntities.RESERVOIR_DOGS,MockedEntities.PEPA);
        Assertions.assertThat(fromRepository).isNotNull();
        Assertions.assertThat(fromRepository).usingRecursiveComparison().isEqualTo(MockedEntities.PEPA_RESERVOIR_REVIEW);
    }

    @Test
    public void findByMovieAndUserNullMovie() {
        Assertions.assertThatThrownBy(() -> service.findByMovieAndUser(null, MockedEntities.PEPA)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findByMovieNullAndUser() {
        Assertions.assertThatThrownBy(() -> service.findByMovieAndUser(MockedEntities.RESERVOIR_DOGS, null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findByMovieAndUserBothNull() {
        Assertions.assertThatThrownBy(() -> service.findByMovieAndUser(null, null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findAll() {
        List<Review> fromRepository = service.findAll();

        Assertions.assertThat(fromRepository).isNotNull();
        Assertions.assertThat(fromRepository).containsExactlyInAnyOrder(MockedEntities.HONZA_RESERVOIR_REVIEW,
                MockedEntities.PEPA_RESERVOIR_REVIEW,MockedEntities.PEPA_MONTY_REVIEW,MockedEntities.KAREL_PULP_REVIEW);
    }

    @Test
    public void delete() {
        service.delete(MockedEntities.HONZA_RESERVOIR_REVIEW);
        Mockito.verify(dao, Mockito.times(1)).delete(MockedEntities.HONZA_RESERVOIR_REVIEW);
    }

    @Test
    public void deleteNull() {
        Assertions.assertThatThrownBy(() -> service.delete(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void deleteNonExistent() {
        Assertions.assertThatThrownBy(() -> service.delete(MockedEntities.NON_EXISTENT_REVIEW)).isInstanceOf(MissingEntityException.class);
    }

}
