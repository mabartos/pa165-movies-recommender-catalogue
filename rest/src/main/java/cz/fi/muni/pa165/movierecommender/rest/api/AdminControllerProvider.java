package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import cz.fi.muni.pa165.movierecommender.api.facade.MovieFacade;
import cz.fi.muni.pa165.movierecommender.api.facade.PersonFacade;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.PersonUpdateDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminControllerProvider implements AdminController{

    private final MovieFacade movieFacade;
    private final PersonFacade personFacade;

    @Autowired
    public AdminControllerProvider(MovieFacade movieFacade,
                                   PersonFacade personFacade) {
        this.movieFacade = movieFacade;
        this.personFacade = personFacade;
    }

    @PostMapping(value = "/create/movie")
    @ResponseBody
    public MovieDto createMovie(@RequestBody MovieCreateDto createDto) {
        return movieFacade.create(createDto);
    }

    @DeleteMapping("/delete/movie/{id}")
    @ResponseBody
    public void deleteMovie(@PathVariable Long id) {
        movieFacade.delete(id);
    }

    @PatchMapping("/update/movie")
    @ResponseBody
    public MovieDto updateMovie(@RequestBody MovieUpdateDto movie) {
        return movieFacade.update(movie);
    }


    @PostMapping(value = "/create/person")
    @ResponseBody
    public PersonDto createPerson(@RequestBody PersonCreateDto createDto) {
        return personFacade.create(createDto);
    }

    @DeleteMapping(value = "/delete/person/{id}")
    @ResponseBody
    public void deletePerson(@PathVariable Long id) {
        personFacade.delete(id);
    }

    @PatchMapping(value = "/update/person")
    @ResponseBody
    public PersonDto updatePerson(@RequestBody PersonUpdateDto person) {
        return personFacade.update(person);
    }
}
