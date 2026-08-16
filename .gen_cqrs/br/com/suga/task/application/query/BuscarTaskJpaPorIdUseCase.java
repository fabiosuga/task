package br.com.suga.task.application.query;

import br.com.suga.task.domain.repository.TaskJpaRepository;
import br.com.suga.task.application.query.dto.TaskJpaResponse;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class BuscarTaskJpaPorIdUseCase {

    private final TaskJpaRepository repository;

    public BuscarTaskJpaPorIdUseCase(TaskJpaRepository repository) {
        this.repository = repository;
    }

    public TaskJpaResponse execute(BuscarTaskJpaPorIdQuery query) {
        Objects.requireNonNull(query, "query nao pode ser nula");
        return repository.findById(query.getId())
                .map(TaskJpaResponse::fromDomain)
                .orElseThrow(() -> new IllegalArgumentException("Registro nao encontrado: " + query.getId()));
    }
}