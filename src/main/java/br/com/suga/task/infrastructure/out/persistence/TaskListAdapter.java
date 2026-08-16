package br.com.suga.task.infrastructure.out.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.repository.TaskFilter;
import br.com.suga.task.domain.repository.TaskRepository;

@Repository
public class TaskListAdapter implements TaskRepository {

    private final List<Task> tasks = new CopyOnWriteArrayList<>();

    @Override
    public Task findById(String id) {
        return tasks.stream()
            .filter(task -> task.getId().value().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @Override
    public List<Task> list(TaskFilter filter) {
        return tasks.stream()
            .filter(task -> filter.title() == null || task.getTitle().value().contains(filter.title()))
            .filter(task -> filter.completed() == null || task.isCompleted() == filter.completed())
            .toList();
    }

    @Override
    public void insert(Task task) {
        tasks.add(task);
    }

    @Override
    public void update(Task task) {
        tasks.stream()
            .filter(t -> t.getId().value().equals(task.getId().value()))
            .findFirst()
            .ifPresentOrElse(
                t -> {
                    tasks.remove(t);
                    tasks.add(task);
                },
                () -> {
                    throw new RuntimeException("Task not found with id: " + task.getId());
                }
            );
    }

    @Override
    public void delete(String id) {
        tasks.stream()
            .filter(task -> task.getId().value().equals(id))
            .findFirst()
            .ifPresentOrElse(
                task -> tasks.remove(task),
                () -> {
                    throw new RuntimeException("Task not found with id: " + id);
                }
            );
    }

    
}
