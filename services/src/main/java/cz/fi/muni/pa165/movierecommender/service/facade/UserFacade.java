package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;

/**
 * @author Daniel Puchala
 */
public interface UserFacade extends GenericFacade<UserCreateDto, UserUpdateDto> {

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

}
