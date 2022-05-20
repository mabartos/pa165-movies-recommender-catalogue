package cz.fi.muni.pa165.movierecommender.api.dto.account;

import lombok.Data;

import javax.validation.constraints.NotNull;

//No mapper needed - extraction directly from this object
@Data
public class LoginDto {

    @NotNull
    private Long id;
    @NotNull
    private String password;
}
