package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.ReviewDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.ReviewUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.service.mapper.ReviewMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.create.ReviewCreateMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.update.ReviewUpdateMapper;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import cz.fi.muni.pa165.movierecommender.service.service.MovieService;
import cz.fi.muni.pa165.movierecommender.service.service.ReviewService;
import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import facade.ReviewFacade;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Petr Šlézar
 */
@Service
public class ReviewFacadeImpl extends GenericFacadeImpl<Review, ReviewDto, ReviewCreateDto, ReviewUpdateDto> implements ReviewFacade {

    private final ReviewService reviewService;
    private final UserService userService;
    private final MovieService movieService;

    private final ReviewCreateMapper createMapper = Mappers.getMapper(ReviewCreateMapper.class);
    private final ReviewUpdateMapper updateMapper = Mappers.getMapper(ReviewUpdateMapper.class);
    private final ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);

    @Autowired
    public ReviewFacadeImpl(ReviewService reviewService, UserService userService, MovieService movieService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @Override
    protected GenericService<Review> service() {
        return reviewService;
    }

    @Override
    protected Review mapToEntity(ReviewCreateDto dto) {

        User author = userService.findById(dto.getUserId());
        Movie movie = movieService.findById(dto.getMovieId());

        Review createdReview = createMapper.toModel(dto);
        createdReview.setUser(author);
        createdReview.setMovie(movie);

        return createdReview;
    }

    @Override
    protected ReviewDto mapToDto(Review entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected Review mapToUpdatedEntity(ReviewUpdateDto dto) {

        User author = userService.findById(dto.getUserId());
        Movie movie = movieService.findById(dto.getMovieId());

        Review updatedReview = updateMapper.toModel(dto);
        updatedReview.setUser(author);
        updatedReview.setMovie(movie);

        return updatedReview;
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewDto findById(Long id) {
        if(id == null) throw new BadArgumentException("Id is null!");

        Review entity = service().findById(id);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> findAll() {
        List<Review> found = service().findAll();
        return found.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> findByUser(Long userId) {
        if(userId == null) throw new BadArgumentException("User id is null!");

        User user = userService.findById(userId);

        List<Review> found = reviewService.findByUser(user);
        return found.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> findByMovie(Long movieId) {
        if(movieId == null) throw new BadArgumentException("Movie id is null!");

        Movie movie = movieService.findById(movieId);

        List<Review> found = reviewService.findByMovie(movie);
        return found.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewDto findByMovieAndUser(Long movieId, Long userId) {
        if (userId == null) throw new BadArgumentException("User id is null!");
        if (movieId == null) throw new BadArgumentException("Movie id is null!");

        User user = userService.findById(userId);
        Movie movie = movieService.findById(movieId);

        Review found = reviewService.findByMovieAndUser(movie, user);
        return mapper.toDto(found);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageRating(Long movieId) {
        if (movieId == null) throw new BadArgumentException("Movie id is null!");
        final Movie movie = movieService.findById(movieId);

        return reviewService.getAverageRating(movie);
    }
}
