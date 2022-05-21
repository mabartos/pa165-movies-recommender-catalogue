package cz.fi.muni.pa165.movierecommender.service.mapper.update;

import cz.fi.muni.pa165.movierecommender.api.dto.update.ReviewUpdateDto;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Review;
import org.mapstruct.Mapper;

/**
 * @author Petr Šlézar
 *
 * A simple mapper using mapstruct. Those attributes which are not mapped, will be later added in facade.
 */
@Mapper
public interface ReviewUpdateMapper {

    Review toModel(ReviewUpdateDto dto);

    ReviewUpdateDto toDto(Review value);
}