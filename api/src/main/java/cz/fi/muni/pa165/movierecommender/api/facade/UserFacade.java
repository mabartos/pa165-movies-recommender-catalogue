package cz.fi.muni.pa165.movierecommender.api.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;

import java.util.Optional;


/**
 * @author Daniel Puchala
 */
public interface UserFacade extends GenericFacade<UserDto, UserCreateDto, UserUpdateDto> {

    /**
     * Finds a user with a given email
     *
     * @param email user email
     * @return user with corresponding email
     * @throws IllegalArgumentException when email parameter is null
     */
    UserDto findByEmail(String email);

    /**
     * Finds a user with a given name
     *
     * @param name user's name
     * @return user with corresponding name
     */
    UserDto findByName(String name);

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param user DTO of newly created user
     * @param unencryptedPassword chosen password
     */
    void registerUser(UserCreateDto user, String unencryptedPassword);

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param user DTO of newly created user
     * @param newPassword newly chosen password
     */
    void changeUser(UserUpdateDto user, String newPassword);

    /**
     * Retrieve info about currently authenticated user.
     *
     * @return UserDto dto about currently authenticated user
     */
    UserDto getLoggedInInfo();

    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @return a token of a user when login succeeds
     */
    String login(String username, String password);

    /**
     * Finds a user by its dao-key.
     *
     * @param token user dao key
     */
    Optional<UserDto> findByToken(String token);

    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    void logout(UserDto user);

    /**
     * Checks if the given user is admin.
     *
     * @param user to be checked
     */
    boolean isAdmin(UserDto user);

}
