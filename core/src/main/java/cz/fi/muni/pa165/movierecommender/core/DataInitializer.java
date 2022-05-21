package cz.fi.muni.pa165.movierecommender.core;

import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.persistence.enums.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Transactional
    public void createMovies(){

        Movie shawshankRedemption = new Movie("The Shawshank Redemption",142,
                "https://play-lh.googleusercontent.com/hqVkofgQryL02Z2g_rsKpu923e2IMr_bXH7mCG0D0QOTLWT_a5qpv4zq3XxJ3vpcFm8hNnVkeLR6KYyi4ss",
                new HashSet<Genre>(List.of(Genre.CRIME,Genre.DRAMA)),
                "Mladý bankovní manažer Andy Dufresne (Tim Robbins) je v roce 1947 odsouzen na doživotí za vraždu své " +
                        "ženy a jejího milence, kterou nespáchal. Čeká ho trest v obávané věznici Shawshank. Andy se " +
                        "zde sblíží s černochem Redem (Morgan Freeman), jenž je tu už dvacet let, a během dlouhé doby se" +
                        " jim společně podaří dosáhnout zlepšení zdejších poměrů. Andy se dokonce stane strážcům i " +
                        "nenáviděnému řediteli věznice nepostradatelný jako daňový a finanční poradce.",
                1994, Collections.emptySet(),null,Collections.emptySet());


    }

}
