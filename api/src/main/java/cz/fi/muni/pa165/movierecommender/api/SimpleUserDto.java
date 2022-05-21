package cz.fi.muni.pa165.movierecommender.api;

/**
 * @author Petr Šlézar
 *
 * This class is intended to be used for simplifying the info about user that is carried by other DTO (we don't need
 * such level of detail when displaying review,etc.)
 */
public class SimpleUserDto {

    private String name;
    private String avatar;
    private Long Id;
}
