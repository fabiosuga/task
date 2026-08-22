package br.com.suga.task.application.usecase.delete;

import br.com.suga.task.application.port.TaskRepositoryProvider;
import br.com.suga.task.application.usecase.StorageMode;
import br.com.suga.task.shared.UseCaseInterface;

public class DeleteTaskUseCase implements UseCaseInterface<String, Void> {
    
    private TaskRepositoryProvider repositoryProvider;
    private StorageMode storageMode;

    public DeleteTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
        storageMode = StorageMode.LIST;
    }

    @Override
    public Void execute(String id) {
        // pre validation ?
        
        repositoryProvider
            .resolve(storageMode)
            .delete(id);

        return null;
    }
    
}
