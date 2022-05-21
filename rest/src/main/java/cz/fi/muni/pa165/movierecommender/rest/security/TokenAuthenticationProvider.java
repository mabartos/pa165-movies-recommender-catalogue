package cz.fi.muni.pa165.movierecommender.rest.security;

import cz.fi.muni.pa165.movierecommender.api.account.UserDto;
import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Optional;

/**
 * @author Jakub Petras | xpetras@mail.muni.cz
 *         5/9/21
 * Inspired by https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/#jwt-example-1
 */
@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @NonNull
    private final UserService accountService;

    @Autowired
    public TokenAuthenticationProvider(UserService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
        // Nothing to do
    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {
        final Object token = authentication.getCredentials();
        UserDto dto = Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(accountService::extractUserFromToken)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
        return accountService.findByName(dto.getName());
    }
}
