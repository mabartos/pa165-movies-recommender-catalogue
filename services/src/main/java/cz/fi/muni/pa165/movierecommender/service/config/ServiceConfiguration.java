package cz.fi.muni.pa165.movierecommender.service.config;

import cz.fi.muni.pa165.movierecommender.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceConfig.class})
@ComponentScan({"cz.fi.muni.pa165.movierecommender.service"})
public class ServiceConfiguration {

}
