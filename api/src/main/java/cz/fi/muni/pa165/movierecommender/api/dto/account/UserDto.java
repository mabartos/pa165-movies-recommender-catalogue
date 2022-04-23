package cz.fi.muni.pa165.movierecommender.api.dto.account;

import cz.fi.muni.pa165.movierecommender.api.enums.UserType;

/**
 * @author Daniel Puchala
 */
public class UserDto {

        private String email;

        private String name;

        private String passwordHash;

        private UserType userType;

        private String avatar;

        private String about;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public UserType getUserType() {
            return userType;
        }

        public void setUserType(UserType userType) {
            this.userType = userType;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public UserDto() {
        }

        public UserDto(String email, String name, String passwordHash, UserType userType, String avatar, String about) {
            this.email = email;
            this.name = name;
            this.passwordHash = passwordHash;
            this.userType = userType;
            this.avatar = avatar;
            this.about = about;
        }
}
