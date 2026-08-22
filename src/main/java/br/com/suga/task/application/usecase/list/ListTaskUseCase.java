package br.com.suga.task.application.usecase.list;

import java.util.List;

import br.com.suga.task.application.port.TaskRepositoryProvider;
import br.com.suga.task.application.usecase.StorageMode;
import br.com.suga.task.application.usecase.mapper.TaskMapper;
import br.com.suga.task.application.usecase.response.TaskResponse;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.repository.TaskFilter;

import br.com.suga.task.shared.UseCaseInterface;

public class ListTaskUseCase implements UseCaseInterface<FilterTaskInput, List<TaskResponse>> {
    
    private TaskRepositoryProvider repositoryProvider;
    private StorageMode storageMode;
    private TaskMapper taskMapper;

    public ListTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
        taskMapper = new TaskMapper();
        storageMode = StorageMode.LIST;
    }

    @Override
    public List<TaskResponse> execute(FilterTaskInput input) {
        TaskFilter taskFilter = new TaskFilter(
            input.id(),
            input.title(),
            input.description(),
            input.completed()
        );

        List<Task> lst = repositoryProvider
            .resolve(storageMode)
            .list(taskFilter);
        
        List<TaskResponse> responseLst = null;

        if (lst != null && !lst.isEmpty()) {
            responseLst = lst.stream()
                .map(task -> taskMapper.toResponse(task))
                .toList();
        }
        
        return responseLst;
    }
    
}
