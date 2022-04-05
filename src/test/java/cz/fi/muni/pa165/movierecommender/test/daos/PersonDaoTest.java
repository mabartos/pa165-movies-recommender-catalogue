package cz.fi.muni.pa165.movierecommender.test.daos;

import cz.fi.muni.pa165.movierecommender.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.entity.Movie;
import cz.fi.muni.pa165.movierecommender.entity.Person;
import cz.fi.muni.pa165.movierecommender.enums.Genre;
import cz.fi.muni.pa165.movierecommender.test.PersistenceTestApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;

/**
 * @author Petr Slezar
 */
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private PersonDao repository;

    private Person tim;
    private Person quentin;
    private Person michael;
    private Movie reservoirdogs;

    @BeforeClass
    public void setup() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        reservoirdogs = new Movie();
        reservoirdogs.setName("Reservoir Dogs");
        reservoirdogs.setDescription("Tarantino's first really successful film");
        reservoirdogs.setDuration(120);
        reservoirdogs.setReleaseYear(1992);
        reservoirdogs.setPoster("https://img.csfd.cz/files/images/user/profile/159/803/159803949_909f86.jpg");
        HashSet<Genre> genres = new HashSet<>();
        genres.add(Genre.CRIME);
        genres.add(Genre.ACTION);
        reservoirdogs.setGenres(genres);

        em.persist(reservoirdogs);

        tim = new Person();
        tim.setName("Tim Roth");
        tim.setBirth(LocalDate.of(1961, Month.MAY, 14));
        tim.setAbout("About Tim");
        tim.setPicture("https://m.media-amazon.com/images/M/MV5BMjA5NTA3MDQyOV5BMl5BanBnXkFtZTcwODM4NDE3Mw@@._V1_UY1200_CR153,0,630,1200_AL_.jpg");


        quentin = new Person();
        quentin.setName("Quentin Tarantino");
        quentin.setBirth(LocalDate.of(1963, Month.MARCH, 27));
        quentin.setAbout("About Quentin");
        quentin.setPicture("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Quentin_Tarantino_by_Gage_Skidmore.jpg/225px-Quentin_Tarantino_by_Gage_Skidmore.jpg");

        michael = new Person();
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

        em.persist(tim);
        em.persist(quentin);
        em.persist(michael);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void findAll() {
        List<Person> obtained = repository.findAll();

        Assert.assertEquals(obtained.size(),3);
    }


    @Test
    public void findById() {
        Person obtained = repository.findById(quentin.getId());

        Assert.assertNotNull(obtained);
        Assert.assertEquals(obtained,quentin);
    }

    @Test
    public void findByName() {

        List<Person> obtained = repository.findByName("Tim Roth");

        Assert.assertNotEquals(obtained.size(),0);
        Assert.assertEquals(obtained.get(0),tim);
    }

    @Test
    public void create() {
        Person harvey = new Person();
        harvey.setName("Harvey Keitel");
        harvey.setBirth(LocalDate.of(1939, Month.MAY, 13));
        harvey.setAbout("About Harvey");
        harvey.setPicture("http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcToz-PO-Dh-dy4OcPI4u_aKe4XLP_MIVbiZaxtrVD7-8qoW_rk0XzTsRh1mZHXT");
        repository.create(harvey);

        Person obtained = repository.findById(harvey.getId());

        Assert.assertNotNull(obtained);
        Assert.assertEquals(obtained, harvey);
    }

    @Test
    public void update() {
        quentin.setName("Tarantino Quentin");
        repository.update(quentin);

        Person obtained = repository.findById(quentin.getId());

        Assert.assertNotNull(obtained);
        Assert.assertEquals(obtained.getName(),"Tarantino Quentin");
    }

    @Test
    public void delete() {
        Long michaelId = michael.getId();
        repository.delete(michael);

        Person obtained = repository.findById(michaelId);

        Assert.assertNull(obtained);
    }

}
