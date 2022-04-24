package cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.dao;

import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.entity.User;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link UserDao} interface.
 *
 * @author Daniel Puchala
 */
@Repository
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        final List<User> users = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        final List<User> users = em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByReview(Long reviewId) {
        if (reviewId == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        final List<User> users = em.createQuery("select r.user from Review r where r.id = :reviewId", User.class)
                .setParameter("reviewId", reviewId)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }
}
