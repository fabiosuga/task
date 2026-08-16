package br.com.suga.task.infrastructure.out.persistence.mapper;

import br.com.suga.task.domain.model.TaskJpa;
import br.com.suga.task.infrastructure.out.persistence.entity.TaskJpaJpaEntity;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TaskJpaMapper {
    public TaskJpa toDomain(TaskJpaJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TaskJpa(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getCompleted());
    }

    public TaskJpaJpaEntity toEntity(TaskJpa domain) {
        if (domain == null) {
            return null;
        }
        TaskJpaJpaEntity entity = new TaskJpaJpaEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCompleted(domain.getCompleted());
        return entity;
    }

    public List<TaskJpa> toDomainList(List<TaskJpaJpaEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::toDomain).toList();
    }
}
