package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserDto;
import cz.fi.muni.pa165.movierecommender.api.dto.account.UserUpdateDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.security.access.prepost.PreAuthorize;
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

/**
 * @author Daniel Puchala
 */
@RestController
@RequestMapping(RoutesHolder.USER_ROUTE)
public interface UserController {

    @GetMapping
    @ResponseBody
    List<UserDto> findAll();

    @GetMapping("/{id}")
    @ResponseBody
    UserDto findById(@PathVariable Long id);

    @GetMapping("/search")
    @ResponseBody
    UserDto search(@RequestParam String name, @RequestParam String email);

    @GetMapping("/count")
    @ResponseBody
    Long getCount();

    @PostMapping
    @ResponseBody
    UserDto create(@RequestBody UserCreateDto createDto);

    @PreAuthorize("hasAuthority('TYPE_ADMIN')")
    @DeleteMapping("{id}")
    @ResponseBody
    void delete(@PathVariable Long id);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/me")
    @ResponseBody
    void deleteMe();

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    @ResponseBody
    UserDto getMyInfo();

    @PreAuthorize("isAuthenticated()")
    @PatchMapping
    @ResponseBody
    UserDto update(@RequestBody UserUpdateDto user);

    @GetMapping("{id}/reviews")
    @ResponseBody
    List<ReviewDto> getUserReviews(@PathVariable Long id);
}
