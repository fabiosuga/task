package br.com.suga.task.application.usecase.insert;

import java.util.UUID;
import br.com.suga.task.application.port.TaskRepositoryProvider;
import br.com.suga.task.application.usecase.StorageMode;
import br.com.suga.task.application.usecase.mapper.TaskMapper;
import br.com.suga.task.application.usecase.response.TaskResponse;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.shared.UseCaseInterface;

public class InsertTaskUseCase implements UseCaseInterface<InsertTaskInput, TaskResponse> {
    
    private StorageMode storageMode;
    private TaskMapper taskMapper;
    private TaskRepositoryProvider repositoryProvider;

    public InsertTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
        taskMapper = new TaskMapper();
        
        // For now, we are using LIST as the default storage mode for inserting tasks.
        storageMode = StorageMode.LIST;
    }

    @Override
    public TaskResponse execute(InsertTaskInput input) {
        Task task = taskMapper.toInsertDomain(UUID.randomUUID(), input);
        
        try {
            repositoryProvider
                .resolve(storageMode)
                .insert(task);
        } catch (Exception e) {
            throw new RuntimeException("Error inserting task: " + e.getMessage());
        }

        return taskMapper.toResponse(task);
    }
    
}
