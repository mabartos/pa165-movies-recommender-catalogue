package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RoutesHolder.REVIEW_ROUTE)
public interface ReviewController {

    @GetMapping
    @ResponseBody
    List<ReviewDto> findAll();

    @GetMapping("/search")
    @ResponseBody
    ReviewDto findByMovieAndUser(@RequestParam Long movieId, @RequestParam Long userId);

    @GetMapping("/create")
    @ResponseBody
    ReviewDto createReview(@RequestBody ReviewCreateDto reviewCreateDto);
}
