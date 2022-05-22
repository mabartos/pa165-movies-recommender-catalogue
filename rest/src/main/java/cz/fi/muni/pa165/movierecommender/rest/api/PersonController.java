package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.PersonUpdateDto;
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
 * @author Maxim Svistunov
 */
@RestController
@RequestMapping(RoutesHolder.PERSON_ROUTE)
public interface PersonController {

    @GetMapping
    @ResponseBody
    List<PersonDto> findAll();

    @GetMapping("/{id}")
    @ResponseBody
    PersonDto findById(@PathVariable Long id);

    @GetMapping("/search")
    @ResponseBody
    List<PersonDto> findByName(@RequestParam String name);

    @GetMapping("/count")
    @ResponseBody
    Long getCount();

    @PreAuthorize("hasAuthority('TYPE_ADMIN')")
    @PostMapping
    @ResponseBody
    PersonDto create(@RequestBody PersonCreateDto createDto);

    @PreAuthorize("hasAuthority('TYPE_ADMIN')")
    @DeleteMapping("{id}")
    @ResponseBody
    void delete(@PathVariable Long id);

    @PreAuthorize("hasAuthority('TYPE_ADMIN')")
    @PatchMapping
    @ResponseBody
    PersonDto update(@RequestBody PersonUpdateDto person);
}
