package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.api.enums.Genre;
import cz.fi.muni.pa165.movierecommender.persistence.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.movierecommender.service.service.PersonService;
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

// TODO complete tests
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonServiceTest extends AbstractTestNGSpringContextTests {

    private PersonDao personDao;

    @Autowired
    PersonService personService;

    @BeforeMethod
    public void setup() {
        personDao = mock(PersonDao.class);

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

        when(personDao.findAll()).thenReturn(java.util.Arrays.stream(Arrays.array(tim, quentin, michael)).toList());
    }

    @Test
    public void findAll() {
        List<Person> personList = personDao.findAll();
        assertEquals(personList.size(), 3);
    }
}
