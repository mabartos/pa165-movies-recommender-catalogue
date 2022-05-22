package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.MovieDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(uses = {PersonMapper.class, ReviewMapper.class})
public interface MovieMapper {
    Movie toModel(MovieDto dto);

    MovieDto toDto(Movie value);

}
