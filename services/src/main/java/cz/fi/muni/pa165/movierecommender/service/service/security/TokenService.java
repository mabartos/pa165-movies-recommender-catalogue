package cz.fi.muni.pa165.movierecommender.service.service.security;

import java.util.Map;

/**
 * @author Petr Šlézar
 */
public interface TokenService {

    /**
     * Checks validity of attributes, if are between permanent credentials
     * @param attributes to be checked
     * @return jwt token
     */
    String permanent(Map<String, String> attributes);

    /**
     * Checks validity of attributes, if are still up to date
     * @param attributes to be checked
     * @return jwt token
     */
    String expiring(Map<String, String> attributes);

    /**
     * Checks the validity of the given credentials.
     *
     * @return attributes if verified
     */
    Map<String, String> untrusted(String token);

    /**
     * Checks the validity of the given credentials.
     *
     * @return attributes if verified
     */
    Map<String, String> verify(String token);
}
