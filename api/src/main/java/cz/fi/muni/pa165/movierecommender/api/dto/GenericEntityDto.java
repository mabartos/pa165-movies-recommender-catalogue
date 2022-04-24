package cz.fi.muni.pa165.movierecommender.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Petr Šlézar
 *
 * Common superclass DTO for all entity responses that should contain all entity's metadata.
 */
@Getter
@Setter
public abstract class GenericEntityDto implements Serializable {

    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected int version;
}
