package cz.muni.pa165.movierecommender.test;

import cz.fi.muni.pa165.movierecommender.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.entity.Movie;
import cz.fi.muni.pa165.movierecommender.enums.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Martin Bartos
 */
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
public class MovieTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    protected MovieDao movieDao;

    @Test
    public void create() {
        Movie movie = createDefaultMovie();

        Movie found = movieDao.findById(movie.getId());
        assertEquals(movie, found);
        assertEquals(movieDao.count(), 1);
    }

    @Test
    public void delete() {
        createDefaultMovie("first");
        Movie second = createDefaultMovie("second");

        assertEquals(movieDao.count(), 2);

        movieDao.delete(second);

        assertEquals(movieDao.count(), 1);
    }

    @Test
    public void update() {
        Movie first = createDefaultMovie("first");

        assertNotNull(first);
        assertEquals(first.getName(), "first");

        first.setName("first1");

        movieDao.update(first);

        Movie found = movieDao.findById(first.getId());
        assertNotNull(found);

        assertEquals(found.getName(), "first1");
    }

    @Test
    public void findById() {
        Movie movie = createDefaultMovie();
        assertNotNull(movie);

        assertNotNull(movieDao.findById(movie.getId()));
    }

    @Test
    public void findByName() {
        Movie movie = createDefaultMovie("movie123");
        assertNotNull(movie);

        List<Movie> found = movieDao.findByName("movie123");
        assertEquals(found.size(), 1);

        assertEquals(found.get(0), movie);
    }

    private Movie createDefaultMovie() {
        return createDefaultMovie("Default movie");
    }

    private Movie createDefaultMovie(String name) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription("Descr about movie1");
        movie.setDuration(30);

        Set<Genre> genre = new HashSet<>();
        genre.add(Genre.ACTION);
        genre.add(Genre.ANIMATION);

        movie.setGenres(genre);
        movie.setReleaseYear(2015);

        movieDao.create(movie);
        return movie;
    }
}
