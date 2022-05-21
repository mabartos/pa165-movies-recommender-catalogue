package cz.fi.muni.pa165.movierecommender.service.mapper;

import cz.fi.muni.pa165.movierecommender.api.ReviewDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import org.mapstruct.Mapper;

@Mapper(uses = {SimpleMovieMapper.class,SimpleUserMapper.class})
public interface ReviewMapper {
    Review toModel(ReviewDto dto);

    ReviewDto toDto(Review value);
}
