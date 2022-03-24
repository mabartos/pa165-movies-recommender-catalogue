package dao;

import entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daniel Puchala
 */
@Repository
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao  {


    public UserDaoImpl(Class<User> userClass) {
        super(userClass);
    }

    @Override
    public User findByEmail(String email) {
        return em.find(User.class, email);
    }

    @Override
    public User findByName(String name) {
        return em.find(User.class, name);
    }
}
