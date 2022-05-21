package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.SimpleMovieDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleMovieMapper {

    Movie toModel(SimpleMovieDto dto);

    SimpleMovieDto toDto(Movie value);
}