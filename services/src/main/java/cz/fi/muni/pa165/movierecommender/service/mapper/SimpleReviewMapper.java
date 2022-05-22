package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.dto.SimpleReviewDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import org.mapstruct.Mapper;

@Mapper(uses = {SimpleMovieMapper.class, SimpleUserMapper.class})
public interface SimpleReviewMapper {

    Review toModel(SimpleReviewDto dto);

    SimpleReviewDto toDto(Review value);
}