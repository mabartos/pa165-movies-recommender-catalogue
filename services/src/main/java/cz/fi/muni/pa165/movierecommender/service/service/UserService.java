package cz.fi.muni.pa165.movierecommender.service.service;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.ForbiddenOperationException;
import cz.fi.muni.pa165.movierecommender.service.service.exception.LoginFailedException;

import javax.persistence.EntityExistsException;
import java.util.Optional;

/**
 * @author Daniel Puchala
 * @author Petr Šlézar
 */
public interface UserService extends GenericService<User> {

    /**
     * Finds a user with given email
     *
     * @param email email address of searched user
     * @return a User entity if found, null if not
     * @throws BadArgumentException if email is null
     */
    User findByEmail(String email);

    /**
     * Finds a user with given email
     *
     * @param name name of searched user
     * @return a User entity if found, null if not
     * @throws BadArgumentException if name
     */
    User findByName(String name);

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param user                   created user entity (some fields may be uninitialized)
     * @param unencryptedNewPassword a raw password yet to be encrypted
     * @throws BadArgumentException  if user or unencryptedPassword params are null
     * @throws EntityExistsException if user with given mail or name already exists
     */
    void registerUser(User user, String unencryptedNewPassword);

    /**
     * Update a given user.
     *
     * @param user                       modified user to be updated
     * @param changedUnencryptedPassword new unencrypted password (maybe blank or null) - then no change to it
     * @throws BadArgumentException if user is null
     */
    void updateUser(User user, String changedUnencryptedPassword);

    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @return a user token if login succeeded
     * @throws BadArgumentException if username or login is null
     * @throws LoginFailedException if login failed (due to credentials)
     */
    String login(String username, String password);

    /**
     * Extracts user's login from provided Bearer token.
     * Does not provide user with full info. Just name and token.
     *
     * @param token bearer token value
     * @throws BadArgumentException if token is null
     */
    Optional<UserDto> extractUserFromToken(String token);

    /**
     * Retrieves currently authenticated user.
     *
     * @return user from security context, null if the user is not authenticated
     */
    User getAuthenticatedUser();

    /**
     * Check whether the user is authenticated or not
     *
     * @return true if the user is authenticated
     */
    boolean isUserAuthenticated();

    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    void logout(User user);


}
