package entity;

import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Daniel Puchala
 */
@MappedSuperclass
public class GenericEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @CreationTimestamp
    private Timestamp createdAt;

    @NotNull
    @UpdateTimestamp
    private Timestamp updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.toLocalDateTime();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt.toLocalDateTime();
    }
}
