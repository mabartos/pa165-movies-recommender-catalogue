package cz.fi.muni.pa165.movierecommender.api.dto;


public class SimpleReviewDto extends GenericEntityDto{

    private SimpleUserDto user;
    private SimpleMovieDto movie;
    private Integer scriptRating;
    private Integer ideaRating;
    private Integer visualsEditRating;
    private Integer musicRating;
    private Integer actingRating;
}
