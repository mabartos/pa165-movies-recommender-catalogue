package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import cz.fi.muni.pa165.movierecommender.service.service.UserServiceImpl;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Daniel Puchala
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest extends ServiceTestBase {

    @Mock
    private UserDao userDao;

    UserService service;

    @Override
    protected void assignService() {
        service = new UserServiceImpl(userDao);
    }

    @Override
    protected void mockRepositoryMethods() {
        Mockito.when(userDao.findByEmail("email@mail.muni.cz")).thenReturn(MockedEntities.HONZA);
        Mockito.when(userDao.findByName("xpuchal1")).thenReturn(MockedEntities.KAREL);
        Mockito.when(userDao.findByReview(MockedEntities.KAREL_PULP_REVIEW.getId())).thenReturn(MockedEntities.KAREL);
        Mockito.when(userDao.findAll()).thenReturn(java.util.Arrays.stream(Arrays.array(MockedEntities.HONZA)).toList());
        Mockito.when(userDao.findById(MockedEntities.PEPA.getId())).thenReturn(MockedEntities.PEPA);
    }

    @Test
    public void createTest() {
        service.create(MockedEntities.NON_EXISTENT_USER);
        Mockito.verify(userDao, Mockito.times(1)).create(MockedEntities.NON_EXISTENT_USER);
    }

    @Test
    public void createNull() {
        assertThatExceptionOfType(BadArgumentException.class)
                .isThrownBy(() -> service.create(null))
                .withMessage("Provided Entity is null");
    }

    @Test
    public void createExisting() {
        Assertions.assertThatThrownBy(() -> service.create(MockedEntities.PEPA)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void updateTest() {
        User entityToUpdate = new User(MockedEntities.PEPA.getEmail(), MockedEntities.PEPA.getName(),
                MockedEntities.PEPA.getPasswordHash(), MockedEntities.PEPA.getUserType(),
                MockedEntities.PEPA.getAvatar(), "Some other about");
        entityToUpdate.setId(MockedEntities.PEPA.getId());
        service.update(entityToUpdate);
        Mockito.verify(userDao, Mockito.times(1)).update(entityToUpdate);
    }

    @Test
    public void updateNull() {
        assertThatExceptionOfType(BadArgumentException.class)
                .isThrownBy(() -> service.update(null))
                .withMessage("Provided Entity is null");
    }

    @Test
    public void updateNonExistent() {
        Assertions.assertThatThrownBy(() -> service.update(MockedEntities.NON_EXISTENT_USER)).isInstanceOf(MissingEntityException.class);
    }

    @Test
    public void deleteTest() {
        service.delete(MockedEntities.PEPA);
        Mockito.verify(userDao, Mockito.times(1)).delete(MockedEntities.PEPA);
    }

    @Test
    public void deleteNull() {
        assertThatExceptionOfType(BadArgumentException.class)
                .isThrownBy(() -> service.delete(null))
                .withMessage("Provided Entity is null");
    }

    @Test
    public void deleteNonExistent() {
        Assertions.assertThatThrownBy(() -> service.delete(MockedEntities.NON_EXISTENT_USER)).isInstanceOf(MissingEntityException.class);
    }

    @Test
    public void findById() {
        User fromRepository = service.findById(MockedEntities.PEPA.getId());
        Assertions.assertThat(fromRepository).isNotNull();
        Assertions.assertThat(fromRepository).usingRecursiveComparison().isEqualTo(MockedEntities.PEPA);
    }

    @Test
    public void findByIdNull() {
        Assertions.assertThatThrownBy(() -> service.findById(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findAllTest() {
        List<User> userList = service.findAll();
        assertEquals(1, userList.size());
    }


    @Test
    public void findByEmailTest() {
        assertEquals(MockedEntities.HONZA, service.findByEmail("email@mail.muni.cz"));
    }

    @Test
    public void findByEmailNull() {
        Assertions.assertThatThrownBy(() -> service.findByEmail(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void findByNameTest() {
        assertEquals(MockedEntities.KAREL, service.findByName("xpuchal1"));
    }

    @Test
    public void findByNameNull() {
        Assertions.assertThatThrownBy(() -> service.findByName(null)).isInstanceOf(BadArgumentException.class);
    }
}
