package cz.fi.muni.pa165.movierecommender.rest.security;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Inspired by https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/
 * @author Petr Šlézar
 */
public class NoRedirectStrategy implements RedirectStrategy {

    @Override
    public void sendRedirect(final HttpServletRequest request, final HttpServletResponse response, final String url) throws IOException {
        // No redirect is required with pure REST
    }
}
