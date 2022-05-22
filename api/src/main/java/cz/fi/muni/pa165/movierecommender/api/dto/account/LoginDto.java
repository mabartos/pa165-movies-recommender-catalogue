package cz.fi.muni.pa165.movierecommender.api.dto.account;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotNull
    private String name;
    @NotNull
    private String password;
}
