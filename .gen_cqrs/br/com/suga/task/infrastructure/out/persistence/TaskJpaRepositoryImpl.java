package br.com.suga.task.infrastructure.out.persistence;

import br.com.suga.task.domain.model.TaskJpa;
import br.com.suga.task.domain.repository.TaskJpaRepository;
import br.com.suga.task.infrastructure.out.persistence.entity.TaskJpaJpaEntity;
import br.com.suga.task.infrastructure.out.persistence.mapper.TaskJpaMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TaskJpaRepositoryImpl implements TaskJpaRepository {

    private final EntityManager entityManager;
    private final TaskJpaMapper mapper;

    @Inject
    public TaskJpaRepositoryImpl(EntityManager entityManager, TaskJpaMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public TaskJpa save(TaskJpa domain) {
        TaskJpaJpaEntity entity = mapper.domainToEntity(domain);
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return mapper.entityToDomain(entity);
    }

    @Override
    public Optional<TaskJpa> findById(String id) {
        TaskJpaJpaEntity entity = entityManager.find(TaskJpaJpaEntity.class, id);
        return Optional.ofNullable(mapper.entityToDomain(entity));
    }

    @Override
    public List<TaskJpa> findAll() {
        List<TaskJpaJpaEntity> list = entityManager.createQuery("SELECT e FROM TaskJpaJpaEntity e", TaskJpaJpaEntity.class).getResultList();
        return mapper.listEntityToDomain(list);
    }

    @Override
    public void deleteById(String id) {
        TaskJpaJpaEntity entity = entityManager.find(TaskJpaJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}