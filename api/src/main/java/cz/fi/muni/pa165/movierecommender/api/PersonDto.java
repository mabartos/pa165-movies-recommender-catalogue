package cz.fi.muni.pa165.movierecommender.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
public class PersonDto extends GenericEntityDto{

    private String name;
    private LocalDate birth;
    private String about;
    private String picture;
    private Set<SimpleMovieDto> directedMovies;
    private Set<SimpleMovieDto> actedInMovies;

}
