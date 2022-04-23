package cz.fi.muni.pa165.movierecommender.api.dto;

import lombok.Data;


import java.io.Serializable;

@Data
public class ReviewDto extends GenericEntityDto implements Serializable {

    private SimpleUserDto user;
    private SimpleMovieDto movie;
    private String text;
    private Integer scriptRating;
    private Integer ideaRating;
    private Integer visualsEditRating;
    private Integer musicRating;
    private Integer actingRating;
}