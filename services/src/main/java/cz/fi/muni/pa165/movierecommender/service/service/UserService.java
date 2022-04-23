package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.User;

/**
 * @author Daniel Puchala
 */
public interface UserService extends GenericService<User> {

    User findByEmail(String email);

    User findByName(String name);

    User findByReview(Long reviewId);
}
