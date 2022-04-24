package cz.fi.muni.pa165.movierecommender.persistence.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.api.movierecommender.persistence.entity.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {
    Movie toModel(MovieDto dto);

    MovieDto toDto(Movie value);
}
