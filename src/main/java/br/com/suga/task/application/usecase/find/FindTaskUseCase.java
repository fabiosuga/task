package br.com.suga.task.application.usecase.find;

import br.com.suga.task.application.port.TaskRepositoryProvider;
import br.com.suga.task.application.usecase.StorageMode;
import br.com.suga.task.application.usecase.mapper.TaskMapper;
import br.com.suga.task.application.usecase.response.TaskResponse;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.shared.UseCaseInterface;

public class FindTaskUseCase implements UseCaseInterface<String, TaskResponse> {
    
    private TaskRepositoryProvider repositoryProvider;
    private StorageMode storageMode;
    private TaskMapper taskMapper;

    public FindTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
        this.taskMapper = new TaskMapper();
        this.storageMode = StorageMode.LIST;
    }

    @Override
    public TaskResponse execute(String id) {
        Task task = repositoryProvider
            .resolve(storageMode)
            .findById(id);

        TaskResponse response = null;

        if (task != null) {
            response = taskMapper.toResponse(task);
        }
        
        return response;
    }
    
}
