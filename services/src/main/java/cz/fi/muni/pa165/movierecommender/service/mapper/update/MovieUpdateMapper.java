package cz.fi.muni.pa165.movierecommender.service.mapper.update;

import cz.fi.muni.pa165.movierecommender.api.update.MovieUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import org.mapstruct.Mapper;

/**
 * @author Maxim Svistunov
 *
 */
@Mapper
public interface MovieUpdateMapper {

    Movie toModel(MovieUpdateDto dto);

    MovieUpdateDto toDto(Movie value);
}
