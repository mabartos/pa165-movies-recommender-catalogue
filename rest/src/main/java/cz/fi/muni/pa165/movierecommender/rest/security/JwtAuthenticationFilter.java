package cz.fi.muni.pa165.movierecommender.rest.security;

import cz.fi.muni.pa165.movierecommender.service.service.UserService;
import cz.fi.muni.pa165.movierecommender.service.service.exception.BadArgumentException;
import cz.fi.muni.pa165.movierecommender.service.service.security.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;

/**
 * Inspired by https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/
 * @author Petr Šlézar
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer";

    private final JWTTokenService tokenService;

    private final UserService userService;

    @Autowired
    public JwtAuthenticationFilter(JWTTokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String param = ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(request.getParameter("t"));

        final String token = ofNullable(param)
                .map(value -> removeStart(value, BEARER))
                .map(String::trim)
                .orElse(null);

        if (token == null) {
            logger.debug("Token is null");
            filterChain.doFilter(request, response);
            return;
        }

        final Map<String, String> map = tokenService.verify(token);
        if (map == null) {
            throw new InvalidTokenException();
        }

        if (!map.containsKey("sub")) {
            throw new BadArgumentException("JWT must contain 'sub'");
        }

        final UserDetails details = userService.findById(Long.parseLong(map.get("sub")));

        final Authentication auth = new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}
