package br.com.suga.task.domain.repository;

import br.com.suga.task.domain.model.TaskJpa;
import java.util.List;
import java.util.Optional;

public interface TaskJpaRepository {
    TaskJpa save(TaskJpa taskJpa);
    Optional<TaskJpa> findById(String id);
    List<TaskJpa> findAll();
    void deleteById(String id);
}