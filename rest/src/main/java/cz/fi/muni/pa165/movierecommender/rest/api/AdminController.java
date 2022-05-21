package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.api.update.PersonUpdateDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

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