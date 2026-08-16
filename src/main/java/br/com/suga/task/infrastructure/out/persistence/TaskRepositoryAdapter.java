package br.com.suga.task.infrastructure.out.persistence;

import java.util.List;
import org.springframework.stereotype.Repository;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.repository.TaskFilter;
import br.com.suga.task.domain.repository.TaskRepository;

@Repository
public class TaskRepositoryAdapter implements TaskRepository {
    
    public Task findById(String id) {
        return null;
    }
    
    public List<Task> list(TaskFilter filter) {
        return null;
    }

    
    public void insert(Task task) {

    }
    
    public void update(Task task) {

    }

    public void delete(String id) {

    }
}
