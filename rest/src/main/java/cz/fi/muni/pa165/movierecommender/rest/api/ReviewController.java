package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public interface ReviewController {

    @GetMapping
    @ResponseBody
    List<ReviewDto> findAll();

    @GetMapping("/search")
    @ResponseBody
    ReviewDto findByMovieAndUser(@RequestParam Long movieId, @RequestParam Long userId);
}
