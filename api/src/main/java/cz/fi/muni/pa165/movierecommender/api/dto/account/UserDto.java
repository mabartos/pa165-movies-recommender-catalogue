package cz.fi.muni.pa165.movierecommender.api.dto.account;

import cz.fi.muni.pa165.movierecommender.api.enums.UserType;
import lombok.Data;

/**
 * @author Daniel Puchala
 */

@Data
public class UserDto {

        private String email;
        private String name;
        private String passwordHash;
        private UserType userType;
        private String avatar;
        private String about;
}
