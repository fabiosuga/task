package br.com.suga.task.domain.repository;

import java.util.List;
import br.com.suga.task.domain.model.Task;

public interface TaskRepository {
    Task findById(String id);
    List<Task> list(TaskFilter filter);
    void insert(Task task);
    void update(Task task);
    void delete(String id);
}
