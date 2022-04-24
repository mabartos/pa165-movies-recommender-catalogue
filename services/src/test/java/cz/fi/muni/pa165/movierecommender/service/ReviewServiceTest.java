package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.ReviewDao;
import cz.fi.muni.pa165.movierecommender.service.service.ReviewService;
import cz.fi.muni.pa165.movierecommender.service.service.ReviewServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReviewServiceTest extends ServiceTest{

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
        Mockito.verify(dao, Mockito.times(1)).create(MockedEntities.NON_EXISTENT_REVIEW);
    }



}
