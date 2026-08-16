package br.com.suga.task.application.command;

import br.com.suga.task.domain.model.TaskJpa;
import br.com.suga.task.domain.repository.TaskJpaRepository;
import br.com.suga.task.application.query.dto.TaskJpaResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Objects;

@ApplicationScoped
public class AtualizarTaskJpaUseCase {

    private final TaskJpaRepository repository;

    public AtualizarTaskJpaUseCase(TaskJpaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TaskJpaResponse execute(AtualizarTaskJpaCommand command) {
        Objects.requireNonNull(command, "command nao pode ser nulo");
        repository.findById(command.getId())
                .orElseThrow(() -> new IllegalArgumentException("Registro nao encontrado: " + command.getId()));
        TaskJpa domain = new TaskJpa(command.getId(), command.getTitle(), command.getDescription(), command.getCompleted());
        TaskJpa updated = repository.save(domain);
        return TaskJpaResponse.fromDomain(updated);
    }
}