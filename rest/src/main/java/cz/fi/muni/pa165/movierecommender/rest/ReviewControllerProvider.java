package cz.fi.muni.pa165.movierecommender.rest;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.rest.api.ReviewController;
import cz.fi.muni.pa165.movierecommender.service.facade.ReviewFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewControllerProvider implements ReviewController {
    private final ReviewFacade reviewFacade;

    @Autowired
    public ReviewControllerProvider(ReviewFacade reviewFacade) {
        this.reviewFacade = reviewFacade;
    }

    @GetMapping
    @ResponseBody
    public List<ReviewDto> findAll() {
        return reviewFacade.findAll();
    }

    @GetMapping("/search")
    @ResponseBody
    public ReviewDto findByMovieAndUser(@RequestParam Long movieId, @RequestParam Long userId) {
        if (movieId == null || userId == null) {
            throw new IllegalArgumentException("You need to specify movieId and userId parameters");
        }

        return reviewFacade.findByMovieAndUser(movieId, userId);
    }
}