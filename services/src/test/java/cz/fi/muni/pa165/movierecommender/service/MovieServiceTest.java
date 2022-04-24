package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.movierecommender.service.service.MovieService;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.testng.AssertJUnit.assertEquals;

@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MovieServiceTest extends AbstractTestNGSpringContextTests {
    private MovieDao movieDao;

    @Autowired
    MovieService movieService;

    @BeforeMethod
    public void setup() {
        movieDao = mock(MovieDao.class);

        Movie reservoirdogs = new Movie();
        reservoirdogs.setName("Reservoir Dogs");
        reservoirdogs.setDescription("Tarantino's first really successful film");
        reservoirdogs.setDuration(120);
        reservoirdogs.setReleaseYear(1992);
        reservoirdogs.setPoster("https://img.csfd.cz/files/images/user/profile/159/803/159803949_909f86.jpg");
        HashSet<Genre> genres = new HashSet<>();
        genres.add(Genre.CRIME);
        genres.add(Genre.ACTION);
        reservoirdogs.setGenres(genres);

        Movie dogs = new Movie();
        dogs.setName("Dogs");
        // dogs.setGenres(new HashSet<>(Genre.CRIME, Genre.ACTION));
        dogs.setDuration(75);

        Movie fiction = new Movie();
        fiction.setName("Pulp Fiction");
        dogs.setDuration(145);

        Movie jackie = new Movie();
        jackie.setName("Jackie Brown");
        jackie.setDuration(154);

        Person tim = new Person();
        tim.setName("Tim Roth");
        tim.setBirth(LocalDate.of(1961, Month.MAY, 14));
        tim.setAbout("About Tim");
        tim.setPicture("https://m.media-amazon.com/images/M/MV5BMjA5NTA3MDQyOV5BMl5BanBnXkFtZTcwODM4NDE3Mw@@._V1_UY1200_CR153,0,630,1200_AL_.jpg");


        Person quentin = new Person();
        quentin.setName("Quentin Tarantino");
        quentin.setBirth(LocalDate.of(1963, Month.MARCH, 27));
        quentin.setAbout("About Quentin");
        quentin.setPicture("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Quentin_Tarantino_by_Gage_Skidmore.jpg/225px-Quentin_Tarantino_by_Gage_Skidmore.jpg");

        Person michael = new Person();
        michael.setName("Michael Madsen");
        michael.setBirth(LocalDate.of(1957, Month.SEPTEMBER, 25));
        michael.setAbout("About Michael");
        michael.setPicture("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ0-eFDys64AccHCm7s8vnoVwDFh95EFa56Gs2JPJAexGc_xNKb");

        reservoirdogs.setDirector(quentin);
        HashSet<Person> actors = new HashSet<>();
        actors.add(tim);
        actors.add(quentin);
        actors.add(michael);
        reservoirdogs.setActors(actors);

        when(movieDao.findAll()).thenReturn(java.util.Arrays.stream(Arrays.array(dogs, fiction, jackie)).toList());
        when(movieDao.findByName("Dogs")).thenReturn(java.util.Arrays.stream(Arrays.array(dogs, reservoirdogs)).toList());
    }

    @Test
    public void findAll() {
        List<Movie> movieList = movieDao.findAll();
        assertEquals(movieList.size(), 3);
    }

    @Test
    public void findByName() {
        List<Movie> movieList = movieDao.findByName("Dogs");
        assertEquals(movieList.size(), 2);
    }
}
