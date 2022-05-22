package cz.fi.muni.pa165.movierecommender.service.service;

import com.google.common.collect.ImmutableMap;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.persistence.dao.EntityDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.persistence.enums.UserType;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.ForbiddenOperationException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.LoginFailedException;
import cz.fi.muni.pa165.movierecommender.service.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

/**
 * @author Daniel Puchala
 * @author Petr Šlézar - security and error handling
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    private final UserDao userDao;
    private final TokenService tokens;
    private final PasswordEncoder encoder = new Argon2PasswordEncoder();

    @Autowired
    public UserServiceImpl(UserDao userDao, TokenService tokens) {
        super(userDao, User.class);
        this.userDao = userDao;
        this.tokens = tokens;
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) throw new BadArgumentException("Email is null");

        Optional<User> entity = userDao.findByEmail(email);
        if(entity.isEmpty()) return null;
        return entity.get();
    }

    @Override
    public User findByName(String name) {
        if (name == null) throw new BadArgumentException("Name is null");

        Optional<User> entity = userDao.findByName(name);
        if(entity.isEmpty()) return null;
        return entity.get();
    }

    @Override
    public void registerUser(User user, String unencryptedPassword) {
        if(user == null || unencryptedPassword.isBlank() )
            throw new BadArgumentException("User or their password is null or empty");
        if(userDao.findByEmail(user.getEmail()).isPresent())
            throw new EntityExistsException(String.format("User with email %s already exists", user.getEmail()));
        if(userDao.findByName(user.getName()).isPresent())
            throw new EntityExistsException(String.format("User with name %s already exists",user.getName()));

        user.setPasswordHash(encoder.encode(unencryptedPassword));
        userDao.create(user);
    }

    @Override
    public void updateUser(User user, String unencryptedNewPassword) {
        if(user == null) throw new BadArgumentException("User to be updated is null");
        if(!unencryptedNewPassword.isBlank() && !unencryptedNewPassword.isEmpty())
            user.setPasswordHash(encoder.encode(unencryptedNewPassword));

        userDao.update(user);
    }

    @Override
    public boolean isAdmin(User user) {
        return findById(user.getId()).getUserType().equals(UserType.ADMIN);
    }

    @Override
    public EntityDao<User> getEntityDao() {
        return userDao;
    }

    @Override
    public String login(String username, String password) {
        if (username == null) {
            throw new BadArgumentException("Username is null");
        }
        if (password == null) {
            throw new BadArgumentException("Password is null");
        }
        return userDao
                .findByName(username)
                .filter(user -> encoder.matches(password, user.getPassword()))
                .map(user -> tokens.expiring(ImmutableMap.of("name", username, "role", user.getUserType().toString(), "sub", user.getId().toString())))
                .orElseThrow(() -> new LoginFailedException("Invalid user credentials"));
    }

    @Override
    public Optional<UserDto> extractUserFromToken(String token) {
        if (token == null) {
            throw new BadArgumentException("Token is null");
        }

        return Optional.of(tokens.verify(token))
                .map(map -> map.get("name"))
                .map(userName -> new UserDto(token, userName));
    }


    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        throw new ForbiddenOperationException("No authenticated user is found.");
    }

    @Override
    public void logout(User user) {}
}
