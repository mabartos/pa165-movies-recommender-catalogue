package cz.fi.muni.pa165.movierecommender.service.mapper.create;


import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewCreateMapper {

    Review toModel(ReviewCreateDto dto);

    ReviewCreateDto toDto(Review value);
}