package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.mapper.account.UserMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.account.UserCreateMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.account.UserUpdateMapper;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import cz.fi.muni.pa165.movierecommender.api.facade.UserFacade;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Daniel Puchala
 * @author Petr Šlézar
 */
@Service
public class UserFacadeImpl extends GenericFacadeImpl<User, UserDto, UserCreateDto, UserUpdateDto> implements UserFacade {

    private final UserService userService;
    private final UserCreateMapper createMapper = Mappers.getMapper(UserCreateMapper.class);
    private final UserUpdateMapper updateMapper = Mappers.getMapper(UserUpdateMapper.class);
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected GenericService<User> service() {
        return userService;
    }

    @Override
    protected User mapToEntity(UserCreateDto dto) {
        return createMapper.toModel(dto);
    }

    protected User mapToEntity(UserDto dto) {
        return mapper.toModel(dto);
    }

    @Override
    protected UserDto mapToDto(User entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected User mapToUpdatedEntity(UserUpdateDto dto) {
        return updateMapper.toModel(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email is null!");

        User entity = userService.findByEmail(email);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByName(String name) {
        if(name == null) throw new IllegalArgumentException("Email is null!");

        User entity = userService.findByEmail(name);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public void registerUser(UserCreateDto user, String unencryptedPassword) {
        User userEntity = mapToEntity(user);
        userService.registerUser(userEntity,unencryptedPassword);
    }

    @Override
    @Transactional
    public void changeUser(UserUpdateDto user, String newPassword) {
        User userEntity = mapToEntity(user);
        userService.updateUser(userEntity, newPassword);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getLoggedInInfo() {
        User authenticatedUser = userService.getAuthenticatedUser();
        return mapper.toDto(userService.findById(authenticatedUser.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public String login(String username, String password) {
        if (username == null) throw new IllegalArgumentException("User's username is null");
        if (password == null) throw new IllegalArgumentException("User's password is null");
        return userService.login(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findByToken(String token) {
        if (token == null) throw new IllegalArgumentException("User's token is null");
        return userService.extractUserFromToken(token);
    }

    @Override
    @Transactional
    public void logout(UserDto user) {}

    @Override
    @Transactional(readOnly = true)
    public boolean isAdmin(UserDto user) {
        return userService.isAdmin(mapToEntity(user));
    }

}
