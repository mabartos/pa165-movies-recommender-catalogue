package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
