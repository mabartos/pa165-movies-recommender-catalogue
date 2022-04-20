package cz.fi.muni.pa165.movierecommender.service;

import cz.fi.muni.pa165.movierecommender.persistence.entity.GenericEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GenericService<Entity extends GenericEntity> {

    void create(Entity entity);

    void delete(Entity entity);

    void update(Entity entity);

    List<Entity> findAll();

    Entity findById(Long id);

    Long getCount();
}
