package br.com.suga.task.application.query;

import br.com.suga.task.domain.repository.TaskJpaRepository;
import br.com.suga.task.application.query.dto.TaskJpaResponse;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListarTaskJpasUseCase {

    private final TaskJpaRepository repository;

    public ListarTaskJpasUseCase(TaskJpaRepository repository) {
        this.repository = repository;
    }

    public List<TaskJpaResponse> execute(ListarTaskJpasQuery query) {
        Objects.requireNonNull(query, "query nao pode ser nula");
        return repository.findAll().stream()
                .map(TaskJpaResponse::fromDomain)
                .collect(Collectors.toList());
    }
}