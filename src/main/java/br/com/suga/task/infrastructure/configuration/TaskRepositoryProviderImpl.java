package br.com.suga.task.infrastructure.configuration;

import br.com.suga.task.application.port.TaskRepositoryProvider;
import br.com.suga.task.application.usecase.StorageMode;
import br.com.suga.task.domain.repository.TaskRepository;
import br.com.suga.task.infrastructure.out.persistence.TaskListAdapter;
import br.com.suga.task.infrastructure.out.persistence.TaskRepositoryAdapter;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskRepositoryProviderImpl
        implements TaskRepositoryProvider {

    private final TaskListAdapter taskListRepository;
    private final TaskRepositoryAdapter taskRepositoryAdapter;

    public TaskRepositoryProviderImpl(
        TaskListAdapter taskListRepository, 
        TaskRepositoryAdapter taskRepositoryAdapter
    ) {
        this.taskListRepository = taskListRepository;
        this.taskRepositoryAdapter = taskRepositoryAdapter;
    }

    @Override
    public TaskRepository resolve(StorageMode mode) {

        return switch (mode) {
            case LIST -> taskListRepository;
            case JPA -> taskRepositoryAdapter;
        };
    }
}