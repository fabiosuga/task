package br.com.suga.task.infrastructure.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import br.com.suga.task.application.port.TaskRepositoryProvider;
import br.com.suga.task.application.usecase.delete.DeleteTaskUseCase;
import br.com.suga.task.application.usecase.find.FindTaskUseCase;
import br.com.suga.task.application.usecase.insert.InsertTaskUseCase;
import br.com.suga.task.application.usecase.list.ListTaskUseCase;
import br.com.suga.task.application.usecase.update.UpdateTaskUseCase;

@ApplicationScoped
public class UseCaseConfiguration {

    @Produces
    public InsertTaskUseCase insertTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        
        return new InsertTaskUseCase(repositoryProvider);
    }

    @Produces
    @ApplicationScoped
    public UpdateTaskUseCase updateTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        return new UpdateTaskUseCase(repositoryProvider);
    }

    @Produces
    public DeleteTaskUseCase deleteTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        return new DeleteTaskUseCase(repositoryProvider);
    }

    @Produces
    public FindTaskUseCase findTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        return new FindTaskUseCase(repositoryProvider);
    }

    @Produces
    public ListTaskUseCase listTaskUseCase(TaskRepositoryProvider repositoryProvider) {
        return new ListTaskUseCase(repositoryProvider);
    }
}
