package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.MissingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Puchala
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(userDao, User.class);
        this.userDao = userDao;
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) throw new BadArgumentException("Email is null");

        return userDao.findByEmail(email);
    }

    @Override
    public User findByName(String name) {
        if (name == null) throw new BadArgumentException("Name is null");

        return userDao.findByName(name);
    }

    @Override
    public EntityDao<User> getEntityDao() {
        return userDao;
    }
}
