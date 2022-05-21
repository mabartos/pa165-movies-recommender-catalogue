package cz.fi.muni.pa165.movierecommender.service.mapper.create;

import cz.fi.muni.pa165.movierecommender.api.create.MovieCreateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import org.mapstruct.Mapper;

/**
 * @author Maxim Svistunov
 */
@Mapper
public interface MovieCreateMapper {

    Movie toModel(MovieCreateDto dto);

    MovieCreateDto toDto(Movie value);
}
