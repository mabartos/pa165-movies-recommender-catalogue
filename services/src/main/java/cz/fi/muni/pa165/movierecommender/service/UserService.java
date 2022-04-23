package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daniel Puchala
 */
public interface UserService extends GenericService<User> {

    User findByEmail(String email);

    User findByName(String name);

    User findByReview(Long reviewId);
}
