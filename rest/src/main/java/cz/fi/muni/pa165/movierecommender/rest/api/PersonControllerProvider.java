package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import cz.fi.muni.pa165.movierecommender.api.facade.PersonFacade;
import cz.fi.muni.pa165.movierecommender.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RoutesHolder.PERSON_ROUTE)
public class PersonControllerProvider implements PersonController {
    private final PersonFacade personFacade;
    private final PersonService personService;

    @Autowired
    public PersonControllerProvider(PersonFacade personFacade, PersonService personService) {
        this.personFacade = personFacade;
        this.personService = personService;
    }

    @GetMapping
    @ResponseBody
    public List<PersonDto> findAll() {
        return personFacade.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PersonDto findById(@PathVariable Long id) {
        return personFacade.findById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<PersonDto> findByName(@RequestParam String name) {
        return personFacade.findByName(name);
    }

    @GetMapping("/count")
    @ResponseBody
    public Long getCount() {
        return personService.getCount();
    }

    @PostMapping
    @ResponseBody
    public PersonDto create(@RequestBody PersonCreateDto createDto) {
        return personFacade.create(createDto);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        personFacade.delete(id);
    }

    @PatchMapping
    @ResponseBody
    public PersonDto update(@RequestBody PersonUpdateDto person) {
        return personFacade.update(person);
    }
}
