package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.PersonUpdateDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RoutesHolder.ADMIN_ROUTE)
public interface AdminController {

    @PostMapping(value = "/create/movie")
    @ResponseBody
    MovieDto createMovie(@RequestBody MovieCreateDto createDto);

    @DeleteMapping("/delete/movie/{id}")
    @ResponseBody
    void deleteMovie(@PathVariable Long id);

    @PatchMapping("/update/movie")
    @ResponseBody
    MovieDto updateMovie(@RequestBody MovieUpdateDto movie);

    @PostMapping("/create/person")
    @ResponseBody
    PersonDto createPerson(@RequestBody PersonCreateDto createDto);

    @DeleteMapping("/delete/person/{id}")
    @ResponseBody
    void deletePerson(@PathVariable Long id);

    @PatchMapping("/update/person")
    @ResponseBody
    PersonDto updatePerson(@RequestBody PersonUpdateDto person);
}