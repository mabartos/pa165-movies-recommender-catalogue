package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Puchala
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceImpl(EntityDao<User> entityDao, Class<User> userClass) {
        super(entityDao, userClass);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findByReview(Long reviewId) {
        return userDao.findByReview(reviewId);
    }

    @Override
    public EntityDao<User> getEntityDao() {
        return userDao;
    }
}