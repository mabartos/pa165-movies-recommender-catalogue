package cz.fi.muni.pa165.movierecommender.api.movierecommender;

import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Tests for {@link Review}
 *
 * @author Daniel Puchala
 */
@ContextConfiguration(classes = PersistenceTestConfig.class)
@Ignore("Review must be properly set up")
public class ReviewTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    public void reviewTest() {
        Review review;
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            review = new Review();
            review.setText("");
            em.persist(review);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }
}
