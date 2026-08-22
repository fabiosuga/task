package br.com.suga.task.infrastructure.out.persistence;

import java.util.List;

import br.com.suga.task.application.usecase.mapper.TaskMapper;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.repository.TaskFilter;
import br.com.suga.task.domain.repository.TaskRepository;
import br.com.suga.task.infrastructure.out.persistence.mapper.TaskJpaMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskRepositoryAdapter implements TaskRepository {
    
    private final TaskJpaRepository repository;
    private final EntityManager entityManager;


    public TaskRepositoryAdapter(TaskJpaRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Task findById(String id) {
        TaskJpa taskJpa = repository.findById(id);
        
        if (taskJpa == null) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        
        return TaskJpaMapper.toDomain(taskJpa);
    }

    @Override
    public List<Task> list(TaskFilter filter) {
        StringBuilder jpql = new StringBuilder("SELECT t FROM TaskJpa t WHERE 1=1 ");
        if (filter.id() != null) {
            jpql.append("AND t.id = :id ");
        }
        if (filter.title() != null) {
            jpql.append("AND t.title LIKE :title ");
        }
        if (filter.description() != null) {
            jpql.append("AND t.description LIKE :description ");
        }
        if (filter.completed() != null) {
            jpql.append("AND t.completed = :completed ");
        }

        TypedQuery<TaskJpa> query = entityManager.createQuery(jpql.toString(), TaskJpa.class);
        
        if (filter.id() != null) {
            query.setParameter("id", filter.id());
        }
        if (filter.title() != null) {
            query.setParameter("title", "%" + filter.title() + "%");
        }
        if (filter.description() != null) {
            query.setParameter("description", "%" + filter.description() + "%");
        }
        
        if (filter.completed() != null) {
            query.setParameter("completed", filter.completed());
        }

        return query.getResultList().stream()
                .map(TaskJpaMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void insert(Task task) {
        TaskJpa taskJpa = TaskJpaMapper.toJpa(task);
        repository.persist(taskJpa);
    }

    @Override
    @Transactional
    public void update(Task task) {
        TaskJpa existingTask = repository.findById(task.getId().value());
        if (existingTask != null) {
            existingTask.setTitle(task.getTitle().value());
            existingTask.setDescription(task.getDescription().value());
            existingTask.setCompleted(task.isCompleted());
            
            entityManager.merge(existingTask);
        }
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }
}
