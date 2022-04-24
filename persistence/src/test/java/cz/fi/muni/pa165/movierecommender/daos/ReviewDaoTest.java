package cz.fi.muni.pa165.movierecommender.daos;

import cz.fi.muni.pa165.movierecommender.PersistenceTestApplicationContext;
import cz.fi.muni.pa165.movierecommender.persistence.enums.UserType;
import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.ReviewDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceUnit;

/**
 * Tests for {@link ReviewDao}
 *
 * @author Daniel Puchala
 */
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ReviewDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MovieDao movieDao;

    Review review;
    Movie movie;
    User user;


    @BeforeMethod
    public void setup() {
        user = new User("email", "name", "hash",
                UserType.BASIC_USER, "avatar", "about");
        movie = new Movie();
        movie.setName("Name");
        review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setText("Desc");
        review.setActingRating(10);
        review.setIdeaRating(10);
        review.setMusicRating(10);
        review.setScriptRating(10);
        review.setVisualsEditRating(10);

        userDao.create(user);
        movieDao.create(movie);
        reviewDao.create(review);
    }

    @Test
    public void updateTest() {
        review.setText("Test");
        reviewDao.update(review);
        Assert.assertEquals(reviewDao.findById(review.getId()).getText(), "Test");
    }

    @Test
    public void updateNonExisting() {
        Review review = new Review();
        review.setText("Test");
        review.setId(2L);
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> reviewDao.update(review))
                .withMessage("Cannot update non-existent entity");
    }

    @Test
    public void updateNullReview() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.update(null))
                .withMessage("Entity to update is null");
    }

    @Test
    public void delete() {
        Review review2 = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setText("Desc");
        review.setActingRating(10);
        review.setIdeaRating(10);
        review.setMusicRating(10);
        review.setScriptRating(10);
        review.setVisualsEditRating(10);

        reviewDao.create(review2);
        Assert.assertEquals(reviewDao.findAll().size(), 2);

        reviewDao.delete(review2);
        Assert.assertNull(reviewDao.findById(review2.getId()));
        Assert.assertEquals(reviewDao.findAll().size(), 1);
    }

    @Test
    public void deleteNonExisting() {
        Review review2 = new Review();
        review2.setId(1L);
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> reviewDao.delete(review2))
                .withMessage("Cannot delete non-existent entity");
    }

    @Test
    public void deleteNullReview() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.delete(null))
                .withMessage("Entity to delete is null");
    }

    @Test
    public void findAll() {
        Assert.assertEquals(reviewDao.findAll().size(), 1);
    }

    @Test
    public void findById() {
        Review review = new Review();
        reviewDao.create(review);
        Assert.assertEquals(reviewDao.findById(review.getId()), review);
    }

    @Test
    public void findByIdNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.findById(null))
                .withMessage("Id is null");
    }

    @Test
    public void count() {
        Assert.assertEquals(reviewDao.count(), 1);
    }

    @Test
    public void findByUser() {
        assert(reviewDao.findByUser(user).contains(review));
    }

    @Test
    public void findByUserNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.findByUser(null))
                .withMessage("User is null");
    }

    @Test
    public void findByMovie() {
        assert(reviewDao.findByMovie(movie).contains(review));
    }

    @Test
    public void findByMovieNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.findByMovie(null))
                .withMessage("Movie is null");
    }

    @Test
    public void findByMovieAndUser() {
        Assert.assertEquals(reviewDao.findByMovieAndUser(movie, user), review);
    }


    @Test
    public void findByMovieAndUserNullUser() {
        Movie movie = new Movie();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.findByMovieAndUser(movie, null))
                .withMessage("At least on argument is null");
    }

    @Test
    public void findByMovieAndUserNullMovie() {
        User user = new User();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.findByMovieAndUser(null, user))
                .withMessage("At least on argument is null");
    }

    @Test
    public void findByMovieAndUserNullBoth() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> reviewDao.findByMovieAndUser(null, null))
                .withMessage("At least on argument is null");
    }
}