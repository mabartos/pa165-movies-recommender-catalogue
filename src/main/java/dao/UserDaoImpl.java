package dao;

import entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Daniel Puchala
 */
@Repository
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao  {


    public UserDaoImpl() {
        super(User.class);
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
