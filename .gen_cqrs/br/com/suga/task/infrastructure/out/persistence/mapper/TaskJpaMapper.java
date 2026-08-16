package br.com.suga.task.infrastructure.out.persistence.mapper;

import br.com.suga.task.domain.model.TaskJpa;
import br.com.suga.task.infrastructure.out.persistence.entity.TaskJpaJpaEntity;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskJpaMapper {

    public TaskJpa entityToDomain(TaskJpaJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TaskJpa(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getCompleted());
    }

    public TaskJpaJpaEntity domainToEntity(TaskJpa domain) {
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

    public void updateEntity(TaskJpa domain, TaskJpaJpaEntity entity) {
        if (domain == null || entity == null) {
            return;
        }
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCompleted(domain.getCompleted());
    }

    public List<TaskJpa> listEntityToDomain(List<TaskJpaJpaEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }

    public List<TaskJpaJpaEntity> listDomainToEntity(List<TaskJpa> domains) {
        if (domains == null) {
            return List.of();
        }
        return domains.stream().map(this::domainToEntity).collect(Collectors.toList());
    }
}