package br.com.suga.task.infrastructure.configuration;

import java.util.Map;
import org.springframework.stereotype.Component;
import br.com.suga.task.domain.repository.TaskRepository;
import br.com.suga.task.infrastructure.out.persistence.TaskListAdapter;
import br.com.suga.task.infrastructure.out.persistence.TaskRepositoryAdapter;

@Component
public class TaskRepositoryFactory {

    private final Map<TaskRepositoryType, TaskRepository> repositories;

    public TaskRepositoryFactory(
            TaskRepositoryAdapter jpaAdapter,
            TaskListAdapter listAdapter) {

        this.repositories = Map.of(
                TaskRepositoryType.JPA, jpaAdapter,
                TaskRepositoryType.LIST, listAdapter
        );
    }

    public TaskRepository get(TaskRepositoryType type) {
        return repositories.get(type);
    }
}