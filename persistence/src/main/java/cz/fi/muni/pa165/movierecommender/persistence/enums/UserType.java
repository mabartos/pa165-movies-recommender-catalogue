package cz.fi.muni.pa165.movierecommender.persistence.enums;

import lombok.Getter;

/**
 * @author Daniel Puchala
 */
@Getter
public enum UserType {
    BASIC_USER("TYPE_USER"), ADMIN("TYPE_ADMIN");

    private final String userTypeName;

    UserType(String userTypeName) {
        this.userTypeName = userTypeName;
    }
}
