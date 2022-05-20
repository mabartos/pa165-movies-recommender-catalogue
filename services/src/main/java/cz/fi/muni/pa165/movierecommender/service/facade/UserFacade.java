package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.account.LoginDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;

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
     * @throws BadArgumentException when email parameter is null
     */
    UserDto findByEmail(String email);

    /**
     * Finds a user with a given name
     *
     * @param name user name
     * @return user with corresponding name
     * @throws BadArgumentException when name parameter is null
     */
    UserDto findByName(String name);

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param user DTO of newly created user
     * @param unencryptedPassword chosen password
     * @throws BadArgumentException if user or unencryptedPassword are null
     */
    void registerUser(UserCreateDto user, String unencryptedPassword);

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param user DTO of newly created user
     * @param newPassword newly chosen password
     * @throws BadArgumentException if user are null (newPassword can be null -> no update needed)
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
     * @return an {@link Optional} of a user when login succeeds
     */
    Optional<String> login(String username, String password);

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
