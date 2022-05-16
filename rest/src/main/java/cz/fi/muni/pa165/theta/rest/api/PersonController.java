package cz.fi.muni.pa165.theta.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.PersonDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.PersonCreateDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/persons")
public interface PersonController {

    @GetMapping
    @ResponseBody
    List<PersonDto> findAll();

    @GetMapping("/{id}")
    @ResponseBody
    PersonDto findById(@PathVariable Long id);

    @GetMapping("/search")
    @ResponseBody
    PersonDto findByName(@RequestParam String name);

    @GetMapping("/count")
    @ResponseBody
    Long getCount();

    @PostMapping
    @ResponseBody
    PersonDto create(@RequestBody PersonCreateDto createDto);

    @DeleteMapping("{id}")
    @ResponseBody
    void delete(@PathVariable Long id);

    @PatchMapping("/{id}")
    @ResponseBody
    PersonDto update(@PathVariable Long id, @RequestBody PersonDto person);
}
