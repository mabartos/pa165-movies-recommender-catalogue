package cz.muni.pa165.movierecommender.test;

import cz.fi.muni.pa165.movierecommender.entity.Review;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Tests for {@link Review}
 *
 * @author Daniel Puchala
 */
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
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