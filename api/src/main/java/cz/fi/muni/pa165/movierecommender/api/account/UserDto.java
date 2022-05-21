package cz.fi.muni.pa165.movierecommender.api.account;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daniel Puchala
 */
@Data
@NoArgsConstructor
public class UserDto {

        private Long id;
        private String tokenIdentifier;
        private String email;
        private String name;
        private String userType;
        private String avatar;
        private String about;

        public UserDto(String tokenIdentifier, String name){
                this.tokenIdentifier = tokenIdentifier;
                this.name = name;
        }
}
