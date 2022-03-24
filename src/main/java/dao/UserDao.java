package dao;

import entity.User;

import java.util.List;

/**
 * @author Daniel Puchala
 */
public interface UserDao extends EntityDao<User> {

    User findByEmail(String email);

    List<User> findByName(String name);
}
