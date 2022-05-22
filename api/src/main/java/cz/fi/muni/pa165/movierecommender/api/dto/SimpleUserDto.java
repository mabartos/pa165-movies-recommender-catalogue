package cz.fi.muni.pa165.movierecommender.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Petr Šlézar
 * <p>
 * This class is intended to be used for simplifying the info about user that is carried by other DTO (we don't need
 * such level of detail when displaying review,etc.)
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleUserDto extends GenericEntityDto {

    private String name;
    private String avatar;
}
