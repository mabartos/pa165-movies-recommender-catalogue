package cz.fi.muni.pa169.movierecommender.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"cz.fi.muni.pa165.movierecommender","cz.fi.muni.pa165.movierecommender.rest"})
@EnableAutoConfiguration
public class MovieRecommenderApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MovieRecommenderApplication.class, args);
    }
}