package dao;

import entity.User;
import org.springframework.stereotype.Repository;

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
        return em.createQuery("select u from User u where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return em.createQuery("select u from User u where name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
