package cz.fi.muni.pa165.movierecommender.service.facade;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.dto.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.api.dto.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Maxim Svistunov
 * @author Petr Šlézar - many-to-many
 */
@Service
public class MovieFacadeImpl extends GenericFacadeImpl<Movie, MovieDto, MovieCreateDto, MovieUpdateDto> implements MovieFacade {

    private final MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
    private final MovieService movieService;
    private final PersonService personService;
    private final MovieCreateMapper createMapper = Mappers.getMapper(MovieCreateMapper.class);
    private final MovieUpdateMapper updateMapper = Mappers.getMapper(MovieUpdateMapper.class);

    public MovieFacadeImpl(MovieService movieService, PersonService personService) {
        this.movieService = movieService;
        this.personService = personService;
    }

    @Override
    protected GenericService<Movie> service() {
        return movieService;
    }

    @Override
    protected Movie mapToCreatedEntity(MovieCreateDto dto) {
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


    //The director and actors assigned to the Dto already exist in db, if not - MissingEntityException is thrown by
    //.findById() method of Person Service
    @Override
    @Transactional
    public MovieDto create(MovieCreateDto createDto) {

        Movie entity = mapToCreatedEntity(createDto);
        entity.setGenres(createDto.getGenres().stream().map(Genre::valueOf).collect(Collectors.toSet()));

        boolean hasDirector = (createDto.getDirectorId() != null && createDto.getDirectorId() != 0);
        boolean hasActors = !createDto.getActorsIds().isEmpty();

        Person director;
        Set<Person> actors = new HashSet<>();

        if(hasDirector){

            director = personService.findById(createDto.getDirectorId());
            entity.setDirector(director);

            Set<Movie> directedMovies = director.getDirectedMovies();
            directedMovies.add(entity);
            director.setDirectedMovies(directedMovies);

            personService.update(director);

            entity.setDirector(director);
        }

        if(hasActors){

            for(Long actorId : createDto.getActorsIds()){
                Person currentActor = personService.findById(actorId);
                actors.add(currentActor);

                Set<Movie> currentActorMovies = currentActor.getActedInMovies();
                currentActorMovies.add(entity);
                currentActor.setActedInMovies(currentActorMovies);

                personService.update(currentActor);
            }

            entity.setActors(actors);
        }

        return mapToDto(service().create(entity));
    }

    @Override
    @Transactional
    public MovieDto update(MovieUpdateDto updateDto) {

        Movie entity = mapToUpdatedEntity(updateDto);

        entity.setGenres(updateDto.getGenres().stream().map(Genre::valueOf).collect(Collectors.toSet()));

        boolean hasDirector = (updateDto.getDirectorId() != null && updateDto.getDirectorId() != 0);
        boolean hasActors = !updateDto.getActorsIds().isEmpty();

        Person director;
        Set<Person> actors = new HashSet<>();

        if(hasDirector){

            director = personService.findById(updateDto.getDirectorId());
            entity.setDirector(director);

            Set<Movie> directedMovies = director.getDirectedMovies();
            directedMovies.add(entity);
            director.setDirectedMovies(directedMovies);

            personService.update(director);

            entity.setDirector(director);
        }

        if(hasActors){

            for(Long actorId : updateDto.getActorsIds()){
                Person currentActor = personService.findById(actorId);
                actors.add(currentActor);

                Set<Movie> currentActorMovies = currentActor.getActedInMovies();
                currentActorMovies.add(entity);
                currentActor.setActedInMovies(currentActorMovies);

                personService.update(currentActor);
            }

            entity.setActors(actors);
        }

        return mapToDto(service().update(entity));
    }

}
