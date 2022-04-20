package cz.fi.muni.pa165.movierecommender;

import cz.fi.muni.pa165.movierecommender.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan({"cz.fi.muni.pa165.movierecommender"})
public class PersistenceTestApplicationContext extends PersistenceConfig {
}