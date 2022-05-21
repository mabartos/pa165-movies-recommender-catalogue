package cz.fi.muni.pa165.movierecommender.api.create;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
public class PersonCreateDto implements CreateDto {

    @NotNull
    private String name;

    private LocalDate birth;
    private String about;
    private String picture;
}
