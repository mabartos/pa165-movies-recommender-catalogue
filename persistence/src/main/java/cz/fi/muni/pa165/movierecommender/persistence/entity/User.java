package cz.fi.muni.pa165.movierecommender.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.fi.muni.pa165.movierecommender.persistence.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Entity representing single user of the application.
 * Authentication part source: https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/
 *
 * @author Daniel Puchala
 * @author Petr Šlézar - authentication
 */


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "USERS")
public class User extends GenericEntity implements UserDetails {

    @NotBlank(message = "Email cannot be blank or null")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Name cannot be blank or null")
    @Column(unique = true)
    private String name;

    @NotBlank
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserType userType;

    //URL of image
    private String avatar;

    private String about;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Review.class)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<Review> reviews = new HashSet<>();

    @JsonCreator
    public User(@JsonProperty("username") final String username,
                @JsonProperty("password") final String password,
                @JsonProperty("email") final String email,
                @JsonProperty("userType") final String userType,
                @JsonProperty("about") final String about,
                @JsonProperty("avatar") final String avatar) {
        super();
        this.name = requireNonNull(username);
        this.email = requireNonNull(email);
        this.passwordHash = requireNonNull(password);
        this.userType = UserType.valueOf(requireNonNull(userType));
        this.about = about;
        this.avatar = avatar;
    }

    public User(@NotBlank String username, @NotBlank String password, @NotBlank String email, @NotNull UserType userType) {
        this.name = username;
        this.passwordHash = password;
        this.userType = userType;
        this.email = email;
    }

    public User(@NotBlank String username, @NotBlank String password, @NotBlank String email, @NotNull UserType userType, String about, String avatar) {
        this(username, password, email, userType);
        this.about = about;
        this.avatar = avatar;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(name, user.name) && Objects.equals(passwordHash, user.passwordHash) && userType == user.userType && Objects.equals(avatar, user.avatar) && Objects.equals(about, user.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, name, passwordHash, userType, avatar, about);
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userType.getUserTypeName()));
        return authorities;
    }

    //The following 2 methods are kind of redundant - if we used the proper naming of attributes.
    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return name;
    }

    //The following methods are redundant in this kind of app - we have no locking, banning, expiring, etc.
    //Needed for interface.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        return userType.equals(UserType.ADMIN);
    }
}
