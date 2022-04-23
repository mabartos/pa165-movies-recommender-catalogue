package cz.fi.muni.pa165.movierecommender.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Common superclass DTO for all entity responses that should contain all entity's metadata.
 */
@Getter
@Setter
public abstract class GenericEntityDto {

    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected int version;
}
