package cz.fi.muni.pa165.movierecommender.api.dto.account;

import cz.fi.muni.pa165.movierecommender.api.dto.GenericEntityDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daniel Puchala
 */
@Data
@NoArgsConstructor
public class UserDto extends GenericEntityDto {

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
