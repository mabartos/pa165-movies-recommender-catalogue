package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.service.mapper.MovieMapper;
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
public class MovieFacadeImpl extends GenericFacadeImpl<Movie, MovieCreateDto, MovieUpdateDto> implements MovieFacade {

    private final MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
    private final MovieService movieService;

    public MovieFacadeImpl(MovieService movieService) {
        this.movieService = movieService;
    }


    @Override
    protected GenericService<Movie> service() {
        return null;
    }

    @Override
    protected Movie mapToEntity(MovieCreateDto dto) {
        return null;
    }

    @Override
    protected Movie mergeWithEntity(MovieUpdateDto dto, Movie oldEntity) {
        return null;
    }

    @Override
    public List<MovieDto> findByName(String name) {
        List<Movie> found = movieService.findByName(name);
        return found.stream().map(mapper::toDto).collect(Collectors.toList());
    }

}
