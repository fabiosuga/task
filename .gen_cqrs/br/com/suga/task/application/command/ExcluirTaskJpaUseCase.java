package br.com.suga.task.application.command;

import br.com.suga.task.domain.repository.TaskJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Objects;

@ApplicationScoped
public class ExcluirTaskJpaUseCase {

    private final TaskJpaRepository repository;

    public ExcluirTaskJpaUseCase(TaskJpaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(ExcluirTaskJpaCommand command) {
        Objects.requireNonNull(command, "command nao pode ser nulo");
        repository.deleteById(command.getId());
    }
}