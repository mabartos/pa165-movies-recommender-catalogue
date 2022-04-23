package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.SimpleMovieDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleMovieMapper {

    SimpleMovieDto toDto(Movie value);
}