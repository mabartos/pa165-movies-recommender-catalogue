package cz.fi.muni.pa165.movierecommender.api.dto.update;

import java.io.Serializable;

/**
 * Interface is used only to describe type of class and to provide type safety.
 *
 * Implement in DTO that are meant for update operation.
 */
public interface UpdateDto extends Serializable {

    Long getId();
}
