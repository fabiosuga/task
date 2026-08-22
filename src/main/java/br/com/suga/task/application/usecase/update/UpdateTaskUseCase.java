package br.com.suga.task.application.usecase.update;

import br.com.suga.task.application.port.TaskRepositoryProvider;
import br.com.suga.task.application.usecase.StorageMode;
import br.com.suga.task.application.usecase.mapper.TaskMapper;
import br.com.suga.task.application.usecase.response.TaskResponse;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.shared.UseCaseInterface;

public class UpdateTaskUseCase implements UseCaseInterface<UpdateTaskInput, TaskResponse> {
    
    private TaskRepositoryProvider repositoryProvider;
    private StorageMode storageMode;
    private TaskMapper taskMapper;
    
    public UpdateTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
        taskMapper = new TaskMapper();
        storageMode = StorageMode.LIST;
    }

    @Override
    public TaskResponse execute(UpdateTaskInput input) {
        Task originalTask = repositoryProvider
            .resolve(storageMode)
            .findById(input.id());
        taskMapper.toUpdateDomain(originalTask, input);
        
        if (input.completed()) {
            originalTask.completeTask();
        } else {
            originalTask.uncompleteTask();
        }

        try {
            repositoryProvider
                .resolve(storageMode)
                .update(originalTask);
        } catch (Exception e) {
            throw new RuntimeException("Error updating task: " + e.getMessage());
        }

        return taskMapper.toResponse(originalTask);
    }
    
}
