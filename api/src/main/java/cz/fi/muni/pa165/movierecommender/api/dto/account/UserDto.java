package cz.fi.muni.pa165.movierecommender.api.dto.account;

import lombok.Data;

/**
 * @author Daniel Puchala
 */
@Data
public class UserDto {

        private String email;
        private String name;
        private String passwordHash;
        /**
         *   BASIC_USER / ADMIN
         */
        private String userType;
        private String avatar;
        private String about;
}
