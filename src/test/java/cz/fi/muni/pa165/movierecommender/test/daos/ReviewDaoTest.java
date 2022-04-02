package cz.fi.muni.pa165.movierecommender.test.daos;

import cz.fi.muni.pa165.movierecommender.dao.ReviewDao;
import cz.fi.muni.pa165.movierecommender.dao.ReviewDaoImpl;
import cz.fi.muni.pa165.movierecommender.entity.Movie;
import cz.fi.muni.pa165.movierecommender.entity.Review;
import cz.fi.muni.pa165.movierecommender.entity.User;
import cz.fi.muni.pa165.movierecommender.test.PersistenceTestApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceUnit;

/**
 * Tests for {@link ReviewDao}
 *
 * @author Daniel Puchala
 */
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
public class ReviewDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private ReviewDaoImpl dao;

    @BeforeClass
    public void setup() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Review review = new Review();
        Review review2 = new Review();
        Review review3 = new Review();

        dao.create(review);
        dao.create(review2);
        dao.create(review3);
    }

    @Test
    public void updateTest() {
        Review review = new Review();
        review.setText("");
        dao.create(review);
        review.setText("Test");
        dao.update(review);
        Assert.assertEquals(dao.findById(review.getId()).getText(), "Test");
    }

    @Test
    public void updateNonExisting() {
        Review review = new Review();
        review.setText("Test");
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> dao.update(review))
                .withMessage("Cannot update non-existent entity");
    }

    @Test
    public void updateNullReview() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.update(null))
                .withMessage("Entity to update is null");
    }

    @Test
    public void delete() {
        Review review = new Review();
        dao.create(review);
        dao.delete(review);
        Assert.assertNull(dao.findById(review.getId()));
        Assert.assertEquals(dao.findAll().size(), 3);
    }

    @Test
    public void deleteNonExisting() {
        Review review = new Review();
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> dao.update(review))
                .withMessage("Cannot delete non-existent entity");
    }

    @Test
    public void deleteNullReview() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.update(null))
                .withMessage("Entity to delete is null");
    }

    @Test
    public void findAll() {
        Assert.assertEquals(dao.findAll().size(), 3);
    }

    @Test
    public void findById() {
        Review review = new Review();
        dao.create(review);
        Assert.assertEquals(dao.findById(review.getId()), review);
    }

    @Test
    public void findByIdNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.findById(null))
                .withMessage("Id is null");
    }

    @Test
    public void count() {
        Assert.assertEquals(dao.count(), 3);
    }

    @Test
    public void findByUser() {
        User user = new User();

        Review review = new Review();
        review.setUser(user);
        dao.create(review);

        review = new Review();
        review.setUser(user);
        dao.create(review);

        Assert.assertEquals(dao.findByUser(user).size(), 2);
    }

    @Test
    public void findByUserNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.findByUser(null))
                .withMessage("User is null");
    }

    @Test
    public void findByMovie() {
        Movie movie = new Movie();

        Review review = new Review();
        review.setMovie(movie);
        dao.create(review);

        review = new Review();
        review.setMovie(movie);
        dao.create(review);

        Assert.assertEquals(dao.findByMovie(movie).size(), 2);
    }

    @Test
    public void findByMovieNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.findByMovie(null))
                .withMessage("Movie is null");
    }

    @Test
    public void findByMovieAndUser() {
        Movie movie = new Movie();
        User user = new User();

        Review review = new Review();
        review.setUser(user);
        dao.create(review);

        Review review2 = new Review();
        review2.setMovie(movie);
        dao.create(review2);

        Review review3 = new Review();
        review3.setUser(user);
        review3.setMovie(movie);
        dao.create(review3);

        Assert.assertEquals(dao.findByMovieAndUser(movie, user), review3);
    }

    @Test
    public void findByMovieAndUserEmpty() {
        Movie movie = new Movie();
        User user = new User();
        Assert.assertNull(dao.findByMovieAndUser(movie, user));
    }

    @Test
    public void findByMovieAndUserNullUser() {
        Movie movie = new Movie();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.findByMovieAndUser(movie,null))
                .withMessage("At least on argument is null");
    }

    @Test
    public void findByMovieAndUserNullMovie() {
        User user = new User();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.findByMovieAndUser(null, user))
                .withMessage("At least on argument is null");
    }

    @Test
    public void findByMovieAndUserNullBoth() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dao.findByMovieAndUser(null, null))
                .withMessage("At least on argument is null");
    }
}