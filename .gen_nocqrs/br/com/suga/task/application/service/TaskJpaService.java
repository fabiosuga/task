package br.com.suga.task.application.service;

import br.com.suga.task.application.dto.AtualizarTaskJpaRequest;
import br.com.suga.task.application.dto.CriarTaskJpaRequest;
import br.com.suga.task.application.dto.TaskJpaResponse;
import br.com.suga.task.domain.model.TaskJpa;
import br.com.suga.task.domain.repository.TaskJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class TaskJpaService {
    private final TaskJpaRepository repository;

    public TaskJpaService(TaskJpaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TaskJpaResponse criar(CriarTaskJpaRequest request) {
        Objects.requireNonNull(request, "request nao pode ser nulo");
        TaskJpa domain = new TaskJpa(request.getId(), request.getTitle(), request.getDescription(), request.getCompleted());
        return TaskJpaResponse.fromDomain(repository.save(domain));
    }

    @Transactional
    public TaskJpaResponse atualizar(String id, AtualizarTaskJpaRequest request) {
        Objects.requireNonNull(id, "id nao pode ser nulo");
        Objects.requireNonNull(request, "request nao pode ser nulo");
        repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskJpa nao encontrado: " + id));
        TaskJpa domain = new TaskJpa(request.getId(), request.getTitle(), request.getDescription(), request.getCompleted());
        return TaskJpaResponse.fromDomain(repository.save(domain));
    }

    public TaskJpaResponse buscarPorId(String id) {
        Objects.requireNonNull(id, "id nao pode ser nulo");
        return repository.findById(id)
                .map(TaskJpaResponse::fromDomain)
                .orElseThrow(() -> new IllegalArgumentException("TaskJpa nao encontrado: " + id));
    }

    public List<TaskJpaResponse> listar() {
        return repository.findAll().stream()
                .map(TaskJpaResponse::fromDomain)
                .toList();
    }

    @Transactional
    public void excluir(String id) {
        Objects.requireNonNull(id, "id nao pode ser nulo");
        repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskJpa nao encontrado: " + id));
        repository.deleteById(id);
    }
}
