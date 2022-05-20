package cz.fi.muni.pa165.movierecommender.rest;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.rest.api.MovieController;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import facade.MovieFacade;
import facade.ReviewFacade;
import cz.fi.muni.pa165.movierecommender.service.service.MovieService;
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
@RequestMapping(RoutesHolder.MOVIE_ROUTE)
public class MovieControllerProvider implements MovieController {
    private final MovieFacade movieFacade;
    private final MovieService movieService;
    private final ReviewFacade reviewFacade;

    @Autowired
    public MovieControllerProvider(MovieFacade movieFacade,
                                   MovieService movieService,
                                   ReviewFacade reviewFacade) {
        this.movieFacade = movieFacade;
        this.movieService = movieService;
        this.reviewFacade = reviewFacade;
    }

    @GetMapping
    @ResponseBody
    public List<MovieDto> findAll() {
        return movieFacade.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MovieDto findById(@PathVariable Long id) {
        return movieFacade.findById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<MovieDto> findByName(@RequestParam String name) {
        return movieFacade.findByName(name);
    }

    @GetMapping("/count")
    @ResponseBody
    public Long getCount() {
        return movieService.getCount();
    }

    @PostMapping
    @ResponseBody
    public MovieDto create(@RequestBody MovieCreateDto createDto) {
        return movieFacade.create(createDto);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        movieFacade.delete(id);
    }

    @PatchMapping
    @ResponseBody
    public MovieDto update(@RequestBody MovieUpdateDto movie) {
        return movieFacade.update(movie);
    }

    @GetMapping("{id}/recommended")
    @ResponseBody
    public List<MovieDto> getRecommendedMovies(@PathVariable Long id) {
        return movieFacade.getRecommendedByMovie(id);
    }

    @GetMapping("{id}/reviews")
    @ResponseBody
    public List<ReviewDto> getMovieReviews(@PathVariable Long id) {
        return reviewFacade.findByMovie(id);
    }

    @GetMapping("{id}/rating")
    @ResponseBody
    public Double getAverageRating(@PathVariable Long id) {
        return reviewFacade.getAverageRating(id);
    }
}
