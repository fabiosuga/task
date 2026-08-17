package br.com.suga.task.infrastructure.out.persistence;

import java.util.List;
import org.springframework.stereotype.Repository;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.repository.TaskFilter;
import br.com.suga.task.domain.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class TaskRepositoryAdapter implements TaskRepository {
    
    private final TaskJpaRepository repository;
    private final EntityManager entityManager;


    public TaskRepositoryAdapter(TaskJpaRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Task findById(String id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Task> list(TaskFilter filter) {
        StringBuilder jpql = new StringBuilder("SELECT t FROM Task t WHERE 1=1 ");
        if (filter.id() != null) {
            jpql.append("AND t.id = :id ");
        }
        if (filter.title() != null) {
            jpql.append("AND t.title LIKE %:title% ");
        }
        if (filter.description() != null) {
            jpql.append("AND t.description LIKE %:description% ");
        }
        if (filter.completed() != null) {
            jpql.append("AND t.completed = :completed ");
        }

        TypedQuery<Task> query = entityManager.createQuery(jpql.toString(), Task.class);
        
        if (filter.id() != null) {
            query.setParameter("id", filter.id());
        }
        if (filter.title() != null) {
            query.setParameter("title", filter.title());
        }
        if (filter.description() != null) {
            query.setParameter("description", filter.description());
        }
        
        if (filter.completed() != null) {
            query.setParameter("completed", filter.completed());
        }

        return query.getResultList();
    }

    @Override
    public void insert(Task task) {
        repository.save(task);
    }

    @Override
    public void update(Task task) {
        repository.save(task);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
