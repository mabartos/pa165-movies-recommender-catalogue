package cz.fi.muni.pa165.movierecommender.rest.core;

import java.util.List;

public class RoutesHolder {

    public static final String LOGIN_ROUTE = "/login"; // PUBLIC route

    public static final String USER_ROUTE = "/user";
    public static final String MOVIE_ROUTE = "/movie";
    public static final String PERSON_ROUTE = "/person";
    public static final String REVIEW_ROUTE = "/review";

    public static final List<String> REST_PROTECTED_ROUTES = List.of(
            USER_ROUTE,
            MOVIE_ROUTE,
            PERSON_ROUTE,
            REVIEW_ROUTE
    );

}
