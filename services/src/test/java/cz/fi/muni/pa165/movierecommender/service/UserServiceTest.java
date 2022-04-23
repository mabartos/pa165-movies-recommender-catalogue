package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.api.enums.UserType;
import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.config.ServiceConfiguration;
import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import org.assertj.core.util.Arrays;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Daniel Puchala
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    private User user;
    private Review review;

    @BeforeMethod
    public void setup() {
        user = new User("email", "name", "hash",
                UserType.BASIC_USER, "avatar", "about");

        Movie movie = new Movie();
        movie.setName("Movie");
        movie.setDescription("Movie description");

        review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setText("Very good movie");
        review.setId(1L);

        MockitoAnnotations.openMocks(this);
        when(userDao.findById(99L)).thenReturn(user);
        when(userDao.findByEmail("email@mail.muni.cz")).thenReturn(user);
        when(userDao.findByName("xpuchal1")).thenReturn(user);
        when(userDao.findByReview(review.getId())).thenReturn(user);
        when(userDao.findAll()).thenReturn(java.util.Arrays.stream(Arrays.array(user)).toList());
    }

    @Test
    public void findByEmailTest() {
        assertEquals(user, userService.findByEmail("email@mail.muni.cz"));
    }

    @Test
    public void findByNameTest() {
        assertEquals(user, userService.findByName("xpuchal1"));
    }

    @Test
    public void findByReviewTest() {
        assertEquals(user, userService.findByReview(review.getId()));
    }

    @Test
    public void createTest() {
        userService.create(user);
        Mockito.verify(userDao, Mockito.times(1)).create(user);
    }

    //TODO exception
    @Test
    public void createNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userService.create(null))
                .withMessage("Provided Entity is null");
    }

    @Test
    public void updateTest() {
        user.setUserType(UserType.ADMIN);
        userService.update(user);
        Mockito.verify(userDao, Mockito.times(1)).update(user);
    }

    //TODO exception
    @Test
    public void updateNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userService.update(null))
                .withMessage("Provided Entity is null");
    }

    @Test
    public void deleteTest() {
        userService.delete(user);
        Mockito.verify(userDao, Mockito.times(1)).delete(user);
    }

    //TODO exception
    @Test
    public void deleteNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userService.delete(null))
                .withMessage("Provided Entity is null");
    }

    @Test
    public void findAllTest() {
        List<User> userList = userService.findAll();
        assertEquals(1, userList.size());
    }
}
