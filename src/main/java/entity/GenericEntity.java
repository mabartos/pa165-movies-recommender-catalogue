package entity;

import jakarta.validation.constraints.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalTime;

/**
 * @author Daniel Puchala
 */
public class GenericEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalTime createdAt;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalTime updatedAt;
}
