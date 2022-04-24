package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.persistence.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Test base for every Service test.
 *
 * Every entity service test must contain these tests for DomainService:
 * - create & update
 * - findAll & findById
 * - delete
 *
 * @author Petr Slezar
 */
public abstract class ServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.setMockedEntities();
        this.assignService();
        this.mockRepositoryMethods();
    }

    /**
     * Use this hook to provide implementation to the service interface with mocked DAO object.
     */
    protected abstract void assignService();

    /**
     * Mock all necessary repository method calls in single method to avoid duplicities between test methods.
     *
     */
    protected abstract void mockRepositoryMethods();

    /**
     * Setting relations between persons and movies.
     * Setting also ids, as the repository does not assign it (repository is mocked)
     */
    protected void setMockedEntities(){

        MockedEntities.QUENTIN.setId(1L);
        MockedEntities.TIM.setId(2L);
        MockedEntities.TERRY.setId(3L);
        MockedEntities.NON_EXISTENT_PERSON.setId(Long.valueOf(MockedEntities.NON_EXISTENT_ID));

        MockedEntities.RESERVOIR_DOGS.setId(1L);
        MockedEntities.PULP_FICTION.setId(2L);
        MockedEntities.MONTY_PYTHON.setId(3L);
        MockedEntities.NON_EXISTENT_MOVIE.setId(Long.valueOf(MockedEntities.NON_EXISTENT_ID));


        MockedEntities.PEPA.setId(1L);
        MockedEntities.HONZA.setId(2L);
        MockedEntities.KAREL.setId(3L);
        MockedEntities.NON_EXISTENT_USER.setId(Long.valueOf(MockedEntities.NON_EXISTENT_ID));


        MockedEntities.PEPA_RESERVOIR_REVIEW.setId(1L);
        MockedEntities.HONZA_RESERVOIR_REVIEW.setId(2L);
        MockedEntities.KAREL_PULP_REVIEW.setId(3L);
        MockedEntities.PEPA_MONTY_REVIEW.setId(4L);
        MockedEntities.NON_EXISTENT_REVIEW.setId(Long.valueOf(MockedEntities.NON_EXISTENT_ID));


        MockedEntities.QUENTIN.setActedInMovies(new HashSet<Movie>(List.of(MockedEntities.PULP_FICTION,MockedEntities.RESERVOIR_DOGS)));
        MockedEntities.TIM.setActedInMovies(new HashSet<Movie>(List.of(MockedEntities.PULP_FICTION,MockedEntities.RESERVOIR_DOGS)));
        MockedEntities.TERRY.setActedInMovies(new HashSet<Movie>(List.of(MockedEntities.MONTY_PYTHON)));
        MockedEntities.PULP_FICTION.setActors(new HashSet<Person>(List.of(MockedEntities.QUENTIN,MockedEntities.TIM)));
        MockedEntities.RESERVOIR_DOGS.setActors(new HashSet<Person>(List.of(MockedEntities.QUENTIN,MockedEntities.TIM)));
        MockedEntities.MONTY_PYTHON.setActors(new HashSet<Person>(List.of(MockedEntities.TERRY)));
    }

    protected class MockedEntities {

        public static Integer NON_EXISTENT_ID = -1;
        public static String NON_EXISTENT_NAME = "Non-existent name";

        public static Person QUENTIN =
                new Person("Quentin Tarantino", LocalDate.of(1963, Month.MARCH, 27),
                        "About Tarantino","Tarantino's pic URL",
                        Collections.emptySet(),
                        Collections.emptySet());

        public static Person TIM =
                new Person("Tim Roth",LocalDate.of(1961, Month.MAY, 14),
                        "About Roth","Roth's pic URL",
                        Collections.emptySet(),
                        Collections.emptySet());

        public static Person TERRY =
                new Person("Terry Jones",LocalDate.of(1942, Month.FEBRUARY, 1),
                        "About Terry","Terry's pic URL"
                        ,Collections.emptySet()
                        ,Collections.emptySet());

        public static Person NON_EXISTENT_PERSON = new Person("Ja Veliky",
                LocalDate.of(1999, Month.FEBRUARY, 11),"O mne","Muj obrazek",
                Collections.emptySet(),
                Collections.emptySet());

        public static Movie RESERVOIR_DOGS = new Movie("Reservoir dogs",120,"reservoir dogs poster",
                new HashSet<Genre>(List.of(new Genre[]{Genre.ACTION, Genre.CRIME, Genre.THRILLER})),
                "About Reservoir Dogs",1992,Collections.emptySet(),QUENTIN,Collections.emptySet());
        public static Movie MONTY_PYTHON =new Movie("Monty Python and Holy Grail",120,"monty python poster",
                new HashSet<Genre>(List.of(new Genre[]{Genre.COMEDY, Genre.ADVENTURE, Genre.ACTION, Genre.FANTASY, Genre.HISTORY})),
                "About Monty Python and Holy Grail",1982, Collections.emptySet(),TERRY,Collections.emptySet());
        public static Movie PULP_FICTION = new Movie("Pulp fiction",120,"pulp fiction poster",
                new HashSet<Genre>(List.of(new Genre[]{Genre.ACTION, Genre.CRIME, Genre.THRILLER, Genre.SLICE_OF_LIFE})),
                "About Pulp Fiction",1994,Collections.emptySet(),QUENTIN,Collections.emptySet());
        public static Movie NON_EXISTENT_MOVIE = new Movie(NON_EXISTENT_NAME,1,"Non existent poster",
                Collections.emptySet(),
                "About non existent movie",2000, Collections.emptySet(),NON_EXISTENT_PERSON,Collections.emptySet());

        public static User PEPA = new User("pepa@email.com","Pepa","pepovoheslo", UserType.BASIC_USER,"avatar","o pepovi");
        public static User HONZA = new User("honza@email.com","Honza","honzovoheslo", UserType.BASIC_USER,"avatar","o honzovi");
        public static User KAREL = new User("karel@email.com","Karel","karlovoheslo", UserType.ADMIN,"avatar","o karlovi");
        public static User NON_EXISTENT_USER = new User("nejsem@email.com","Nic","zadny", UserType.BASIC_USER,"zadny","nic");


        public static Review PEPA_RESERVOIR_REVIEW = new Review(PEPA,RESERVOIR_DOGS,"Fakt nuda, jsem tupa opice",1,2,3,4,5);
        public static Review HONZA_RESERVOIR_REVIEW = new Review(HONZA,RESERVOIR_DOGS,"Libily se mi tam ty zbraně lol",3,5,3,5,5);
        public static Review KAREL_PULP_REVIEW = new Review(KAREL,PULP_FICTION,"Jsem sofistikovany normie, takže nic pro me",1,1,6,9,1);
        public static Review PEPA_MONTY_REVIEW = new Review(PEPA,MONTY_PYTHON,"Fakt prdel, lol",10,10,10,10,10);
        public static Review NON_EXISTENT_REVIEW = new Review(NON_EXISTENT_USER,NON_EXISTENT_MOVIE,"Nic",10,10,10,10,10);

    }

}
