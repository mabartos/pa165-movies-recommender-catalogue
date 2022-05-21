package cz.fi.muni.pa165.movierecommender.core;

import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.persistence.enums.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
public class DataInitializer {


    private final UserDao systemUserDao;
    private final PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    @Autowired
    public DataInitializer(UserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    @Transactional
    public void createUsers() {

        User admin = new User("admin",passwordEncoder.encode("admin"),"admin@gmail.com", UserType.ADMIN);
        systemUserDao.create(admin);
    }

}
