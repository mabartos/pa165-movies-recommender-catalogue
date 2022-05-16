package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.service.mapper.MovieMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.create.MovieCreateMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.update.MovieUpdateMapper;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import cz.fi.muni.pa165.movierecommender.service.service.MovieService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maxim Svistunov
 */
@Service
public class MovieFacadeImpl extends GenericFacadeImpl<Movie, MovieDto, MovieCreateDto, MovieUpdateDto> implements MovieFacade {

    private final MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
    private final MovieService movieService;
    private final MovieCreateMapper createMapper = Mappers.getMapper(MovieCreateMapper.class);
    private final MovieUpdateMapper updateMapper = Mappers.getMapper(MovieUpdateMapper.class);

    public MovieFacadeImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    protected GenericService<Movie> service() {
        return movieService;
    }

    @Override
    protected Movie mapToEntity(MovieCreateDto dto) {
        return createMapper.toModel(dto);
    }

    @Override
    protected MovieDto mapToDto(Movie entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected Movie mergeWithEntity(MovieUpdateDto dto, Movie oldEntity) {
        return updateMapper.toModel(dto);
    }

    @Override
    public List<MovieDto> findByName(String name) {
        List<Movie> found = movieService.findByName(name);
        return found.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getRecommendedByMovie(Long movieId) {
        return movieService.getRecommendedByMovie(movieId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

}
