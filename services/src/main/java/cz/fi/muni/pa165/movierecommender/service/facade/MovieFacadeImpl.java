package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.service.mapper.MovieMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.create.MovieCreateMapper;
import cz.fi.muni.pa165.movierecommender.service.mapper.update.MovieUpdateMapper;
import cz.fi.muni.pa165.movierecommender.service.service.GenericService;
import cz.fi.muni.pa165.movierecommender.service.service.MovieService;
import cz.fi.muni.pa165.movierecommender.api.facade.MovieFacade;
import cz.fi.muni.pa165.movierecommender.service.service.PersonService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maxim Svistunov
 * @author Petr Šlézar - many-to-many
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
    protected Movie mapToUpdatedEntity(MovieUpdateDto dto) {
        return updateMapper.toModel(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findByName(String name) {
        List<Movie> found = movieService.findByName(name);
        return found.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getRecommendedByMovie(Long movieId) {
        return movieService.getRecommendedByMovie(movieId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public MovieDto create(MovieCreateDto createDto) {
        Movie entity = mapToEntity(createDto);

        entity.setGenres(createDto.getGenres().stream().map(genreName -> Genre.valueOf(genreName)).collect(Collectors.toSet()));


        return mapToDto(service().create(entity));
    }

}
