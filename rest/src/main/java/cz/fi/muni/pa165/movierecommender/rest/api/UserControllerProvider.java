package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import cz.fi.muni.pa165.movierecommender.api.facade.ReviewFacade;
import cz.fi.muni.pa165.movierecommender.api.facade.UserFacade;
import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RoutesHolder.USER_ROUTE)
public class UserControllerProvider implements UserController {
    private final UserFacade userFacade;
    private final UserService userService;
    private final ReviewFacade reviewFacade;

    @Autowired
    public UserControllerProvider(UserFacade userFacade, UserService userService, ReviewFacade reviewFacade) {
        this.userFacade = userFacade;
        this.userService = userService;
        this.reviewFacade = reviewFacade;
    }

    @GetMapping
    @ResponseBody
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDto findById(@PathVariable Long id) {
        return userFacade.findById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public UserDto search(@RequestParam String name, @RequestParam String email) {
        if (name == null && email == null) throw new IllegalArgumentException("You need to specify name or email");
        if (name != null && email != null) throw new IllegalArgumentException("You can specify only one parameter");
        return name != null ? userFacade.findByName(name) : userFacade.findByEmail(email);
    }

    @GetMapping("/count")
    @ResponseBody
    public Long getCount() {
        return userService.getCount();
    }

    @PostMapping
    @ResponseBody
    public UserDto create(@RequestBody UserCreateDto createDto) {
        return userFacade.create(createDto);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        userFacade.delete(id);
    }

    @PatchMapping
    @ResponseBody
    public UserDto update(@RequestBody UserUpdateDto user) {
        return userFacade.update(user);
    }

    @GetMapping("{id}/reviews")
    @ResponseBody
    public List<ReviewDto> getUserReviews(@PathVariable Long id) {
        return reviewFacade.findByUser(id);
    }
}
