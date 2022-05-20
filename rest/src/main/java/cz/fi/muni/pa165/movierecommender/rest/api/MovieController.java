package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
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
@RequestMapping(RoutesHolder.MOVIE_ROUTE)
public interface MovieController {

    @GetMapping
    @ResponseBody
    List<MovieDto> findAll();

    @GetMapping("/{id}")
    @ResponseBody
    MovieDto findById(@PathVariable Long id);

    @GetMapping("/search")
    @ResponseBody
    List<MovieDto> findByName(@RequestParam String name);

    @GetMapping("/count")
    @ResponseBody
    Long getCount();

    @PostMapping
    @ResponseBody
    MovieDto create(@RequestBody MovieCreateDto createDto);

    @DeleteMapping("{id}")
    @ResponseBody
    void delete(@PathVariable Long id);

    @PatchMapping
    @ResponseBody
    MovieDto update(@RequestBody MovieUpdateDto movie);

    @GetMapping("{id}/recommended")
    @ResponseBody
    List<MovieDto> getRecommendedMovies(@PathVariable Long id);

    @GetMapping("{id}/reviews")
    @ResponseBody
    List<ReviewDto> getMovieReviews(@PathVariable Long id);

    @GetMapping("{id}/rating")
    @ResponseBody
    Double getAverageRating(@PathVariable Long id);
}
