package br.com.suga.task.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.suga.task.application.usecase.delete.DeleteTaskUseCase;
import br.com.suga.task.application.usecase.find.FindTaskUseCase;
import br.com.suga.task.application.usecase.insert.InsertTaskUseCase;
import br.com.suga.task.application.usecase.list.ListTaskUseCase;
import br.com.suga.task.application.usecase.update.UpdateTaskUseCase;
import br.com.suga.task.domain.repository.TaskRepository;
import br.com.suga.task.infrastructure.out.persistence.TaskRepositoryAdapter;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public InsertTaskUseCase insertTaskUseCase() {
        return new InsertTaskUseCase();
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase() {
        return new UpdateTaskUseCase();
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase() {
        return new DeleteTaskUseCase();
    }

    @Bean
    public FindTaskUseCase findTaskUseCase() {
        return new FindTaskUseCase();
    }

    @Bean
    public ListTaskUseCase listTaskUseCase() {
        return new ListTaskUseCase();
    }
}
