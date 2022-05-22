package cz.fi.muni.pa165.movierecommender.service.mapper.create;


import cz.fi.muni.pa165.movierecommender.api.dto.create.ReviewCreateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import org.mapstruct.Mapper;

/**
 * @author Petr Šlézar
 * <p>
 * A simple mapper using mapstruct. Those attributes which are not mapped, will be later added in facade.
 */
@Mapper
public interface ReviewCreateMapper {

    Review toModel(ReviewCreateDto dto);

    ReviewCreateDto toDto(Review value);
}