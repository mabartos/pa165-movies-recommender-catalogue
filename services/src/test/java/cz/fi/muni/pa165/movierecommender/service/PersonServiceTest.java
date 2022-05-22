package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.service.service.PersonService;
import cz.fi.muni.pa165.movierecommender.service.service.PersonServiceImpl;
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

import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonServiceTest extends ServiceTestBase {

    PersonService service;
    @Mock
    private PersonDao dao;

    @Override
    protected void assignService() {
        service = new PersonServiceImpl(dao);
    }

    @Override
    protected void mockRepositoryMethods() {
        final Movie reservoirdogs = new Movie();
        reservoirdogs.setName("Reservoir Dogs");
        reservoirdogs.setDescription("Tarantino's first really successful film");
        reservoirdogs.setDuration(120);
        reservoirdogs.setReleaseYear(1992);
        reservoirdogs.setPoster("https://img.csfd.cz/files/images/user/profile/159/803/159803949_909f86.jpg");
        HashSet<Genre> genres = new HashSet<>();
        genres.add(Genre.CRIME);
        genres.add(Genre.ACTION);
        reservoirdogs.setGenres(genres);

        reservoirdogs.setDirector(MockedEntities.QUENTIN);
        HashSet<Person> actors = new HashSet<>();
        actors.add(MockedEntities.TIM);
        actors.add(MockedEntities.QUENTIN);
        actors.add(MockedEntities.TERRY);
        reservoirdogs.setActors(actors);

        Mockito.when(dao.findAll()).thenReturn(List.of(MockedEntities.QUENTIN, MockedEntities.TIM, MockedEntities.TERRY, MockedEntities.NON_EXISTENT_PERSON));

        Mockito.when(dao.findById(MockedEntities.QUENTIN.getId())).thenReturn(MockedEntities.QUENTIN);
        Mockito.when(dao.findById(MockedEntities.TIM.getId())).thenReturn(MockedEntities.TIM);
        Mockito.when(dao.findById(MockedEntities.TERRY.getId())).thenReturn(MockedEntities.TERRY);

        Mockito.when(dao.findByName("Quentin Tarantino")).thenReturn(List.of(MockedEntities.QUENTIN));
        Mockito.when(dao.findByName("Tim Roth")).thenReturn(List.of(MockedEntities.TIM));
        Mockito.when(dao.findByName("Terry Jones")).thenReturn(List.of(MockedEntities.TERRY));
        Mockito.when(dao.findByName("Ja Veliky")).thenReturn(List.of(MockedEntities.NON_EXISTENT_PERSON));
    }

    @Test
    public void findAll() {
        List<Person> personList = service.findAll();
        Assertions.assertThat(personList).isNotNull();
        Assertions.assertThat(personList.size()).isEqualTo(4);
        Assertions.assertThat(personList).containsExactlyInAnyOrder(
                MockedEntities.QUENTIN,
                MockedEntities.TIM,
                MockedEntities.TERRY,
                MockedEntities.NON_EXISTENT_PERSON);

    }

    @Test
    public void createNull() {
        Assertions.assertThatThrownBy(() -> service.create(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void createExisting() {
        Assertions.assertThatThrownBy(() -> service.create(MockedEntities.TIM)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void update() {
        Person entityToUpdate = new Person(MockedEntities.TIM.getName(),
                MockedEntities.TIM.getBirth(),
                "My new about message",
                MockedEntities.TIM.getPicture(),
                MockedEntities.TIM.getDirectedMovies(),
                MockedEntities.TIM.getActedInMovies());

        entityToUpdate.setId(MockedEntities.TIM.getId());
        service.update(entityToUpdate);
        Mockito.verify(dao, Mockito.times(1)).update(entityToUpdate);
    }

    @Test
    public void updateNull() {
        Assertions.assertThatThrownBy(() -> service.update(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void updateNonExistent() {
        Assertions.assertThatThrownBy(() -> service.update(MockedEntities.NON_EXISTENT_PERSON)).isInstanceOf(MissingEntityException.class);
    }

    @Test
    public void findById() {
        Person fromRepository = service.findById(MockedEntities.QUENTIN.getId());
        Assertions.assertThat(fromRepository).isNotNull();
        Assertions.assertThat(fromRepository).usingRecursiveComparison().isEqualTo(MockedEntities.QUENTIN);
    }

    @Test
    public void findByIdNull() {
        Assertions.assertThatThrownBy(() -> service.findById(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void delete() {
        service.delete(MockedEntities.QUENTIN);
        Mockito.verify(dao, Mockito.times(1)).delete(MockedEntities.QUENTIN);
    }

    @Test
    public void deleteNull() {
        Assertions.assertThatThrownBy(() -> service.delete(null)).isInstanceOf(BadArgumentException.class);
    }

    @Test
    public void deleteNonExistent() {
        Assertions.assertThatThrownBy(() -> service.delete(MockedEntities.NON_EXISTENT_PERSON)).isInstanceOf(MissingEntityException.class);
    }
}
