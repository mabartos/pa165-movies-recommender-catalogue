package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.rest.api.ReviewController;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import cz.fi.muni.pa165.movierecommender.api.facade.ReviewFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RoutesHolder.REVIEW_ROUTE)
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

    @Override
    public ReviewDto createReview(ReviewCreateDto reviewCreateDto) {
        if(reviewCreateDto == null) throw new IllegalArgumentException("Create review body cannnot be null");

        return reviewFacade.create(reviewCreateDto);
    }
}