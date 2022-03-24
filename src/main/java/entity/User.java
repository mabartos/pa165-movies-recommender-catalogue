package entity;

import enums.UserType;
import jakarta.validation.constraints.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Daniel Puchala
 */

@Entity
public class User extends GenericEntity {

    @NotNull(message = "Email cannot be null")
    @Column(unique=true)
    private String email;

    @NotNull(message = "Name cannot be null")
    private String name;

    @Enumerated(EnumType.STRING)
    private UserType userType;

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
