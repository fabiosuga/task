package br.com.suga.task.application.command;

import br.com.suga.task.domain.model.TaskJpa;
import br.com.suga.task.domain.repository.TaskJpaRepository;
import br.com.suga.task.application.query.dto.TaskJpaResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Objects;

@ApplicationScoped
public class InserirTaskJpaUseCase {

    private final TaskJpaRepository repository;

    public InserirTaskJpaUseCase(TaskJpaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TaskJpaResponse execute(InserirTaskJpaCommand command) {
        Objects.requireNonNull(command, "command nao pode ser nulo");
        TaskJpa domain = new TaskJpa(null, command.getTitle(), command.getDescription(), command.getCompleted());
        TaskJpa saved = repository.save(domain);
        return TaskJpaResponse.fromDomain(saved);
    }
}