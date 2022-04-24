package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.mapper.UserMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.update.UserCreateMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.update.UserUpdateMapper;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import cz.fi.muni.pa165.movierecommender.service.service.ReviewService;
import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daniel Puchala
 */
public class UserFacadeImpl extends GenericFacadeImpl<User, UserCreateDto, UserUpdateDto> implements UserFacade {

    private final UserService userService;
    private final ReviewService reviewService;
    private final UserCreateMapper createMapper = Mappers.getMapper(UserCreateMapper.class);
    private final UserUpdateMapper updateMapper = Mappers.getMapper(UserUpdateMapper.class);
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    public UserFacadeImpl(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
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

    @Override
    protected User mergeWithEntity(UserUpdateDto dto, User oldEntity) {
        return updateMapper.toModel(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {
        if(email == null) throw new BadArgumentException("Email is null!");

        User entity = userService.findByEmail(email);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByName(String name) {
        if(name == null) throw new BadArgumentException("Email is null!");

        User entity = userService.findByEmail(name);
        return mapper.toDto(entity);
    }

}
