package cz.fi.muni.pa165.movierecommender.api.movierecommender.daos;

import cz.fi.muni.pa165.movierecommender.api.movierecommender.PersistenceTestApplicationContext;
import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;


/**
 * tests for user dao
 *
 * @author Maxim Svistunov
 */
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    private User user1;
    private User user2;

    @BeforeMethod
    public void createUsers() {
        user1 = new User();
        user1.setName("user1");
        user1.setEmail("user1@smth.com");
        user1.setPasswordHash("user1passwordhash");
        userDao.create(user1);

        user2 = new User();
        user2.setName("user2");
        user2.setEmail("user2@smth.com");
        user2.setPasswordHash("user2passwordhash");
        userDao.create(user2);
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();
        assertEquals(userList.size(), 2);
    }

    @Test
    public void testFindByEmail() {
        Optional<User> user = userDao.findByEmail("user1@smth.com");
        assertEquals(user1, user.get());

        user = userDao.findByEmail("user3@smth.com");
        assertEquals(user,Optional.empty());
    }

    @Test
    public void testFindByName() {
        Optional<User> user = userDao.findByName("user2");
        assertEquals(user2, user.get());
    }

}
