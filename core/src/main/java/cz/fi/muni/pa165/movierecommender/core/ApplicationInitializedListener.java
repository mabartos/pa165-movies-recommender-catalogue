package cz.fi.muni.pa165.movierecommender.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationInitializedListener implements ApplicationListener<ContextRefreshedEvent> {

    private final DataInitializer initializer;

    @Autowired
    public ApplicationInitializedListener(DataInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            log.info("------ Application Data Init Begins ... ------");
            initializer.createUsers();
            initializer.createPersons();
            initializer.createMovies();
            initializer.connectFilmsAndPersons();
            log.info("------ Application Data Init Finished. ------");

            log.info("Swagger: http://localhost:8080/pa165/rest/swagger-ui.html");
        }

    }
}
