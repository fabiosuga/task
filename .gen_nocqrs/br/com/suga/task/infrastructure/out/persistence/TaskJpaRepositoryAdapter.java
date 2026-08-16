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
public class TaskJpaRepositoryAdapter implements TaskJpaRepository {
    private final EntityManager entityManager;
    private final TaskJpaMapper mapper;

    @Inject
    public TaskJpaRepositoryAdapter(EntityManager entityManager, TaskJpaMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public TaskJpa save(TaskJpa domain) {
        TaskJpaJpaEntity entity = mapper.toEntity(domain);
        if (entity.getId() == null) {
            entityManager.persist(entity);
            entityManager.flush();
            return mapper.toDomain(entity);
        }
        return mapper.toDomain(entityManager.merge(entity));
    }

    @Override
    public Optional<TaskJpa> findById(String id) {
        TaskJpaJpaEntity entity = entityManager.find(TaskJpaJpaEntity.class, id);
        return Optional.ofNullable(mapper.toDomain(entity));
    }

    @Override
    public List<TaskJpa> findAll() {
        List<TaskJpaJpaEntity> entities = entityManager
                .createQuery("select entity from TaskJpaJpaEntity entity", TaskJpaJpaEntity.class)
                .getResultList();
        return mapper.toDomainList(entities);
    }

    @Override
    public void deleteById(String id) {
        TaskJpaJpaEntity entity = entityManager.find(TaskJpaJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
