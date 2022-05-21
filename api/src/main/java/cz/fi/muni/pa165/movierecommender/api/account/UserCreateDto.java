package cz.fi.muni.pa165.movierecommender.api.account;

import cz.fi.muni.pa165.movierecommender.api.create.CreateDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto implements CreateDto {

    @NotNull
    private String email;

    @NotNull
    private String name;

    private String userType;

    private String avatar;

    private String about;
}
