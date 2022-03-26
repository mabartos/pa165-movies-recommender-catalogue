package dao;

import entity.User;

import java.util.List;

/**
 * DAO interface for user {@link User}
 *
 * @author Daniel Puchala
 */
public interface UserDao extends EntityDao<User> {

    /**
     * Finds a user with corresponding email.
     *
     * @param email Email of the wanted user
     * @return User with given email
     */
    User findByEmail(String email);

    /**
     * Finds a user with corresponding name.
     *
     * @param name Name of the wanted user
     * @return User with given name
     */
    User findByName(String name);
}
