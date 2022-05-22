package cz.fi.muni.pa165.movierecommender.rest.api;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.api.facade.ReviewFacade;
import cz.fi.muni.pa165.movierecommender.rest.core.RoutesHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Maxim Svistunov
 */
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

    @GetMapping("/{id}")
    @ResponseBody
    public List<ReviewDto> findByMovie(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("You need to specify id");
        }

        return reviewFacade.findByMovie(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    @ResponseBody
    public ReviewDto createReview(ReviewCreateDto reviewCreateDto) {
        if (reviewCreateDto == null) throw new IllegalArgumentException("Create review body cannnot be null");

        return reviewFacade.create(reviewCreateDto);
    }
}