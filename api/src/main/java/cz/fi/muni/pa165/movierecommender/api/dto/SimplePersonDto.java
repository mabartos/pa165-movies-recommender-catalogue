package cz.fi.muni.pa165.movierecommender.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class SimplePersonDto extends GenericEntityDto {
    private String name;
    private LocalDate birth;
    private String about;
    private String picture;
    private Set<SimpleMovieDto> directedMovies;
    private Set<SimpleMovieDto> actedInMovies;
}
