package br.com.suga.task.infrastructure.out.persistence;

import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import br.com.suga.task.domain.repository.TaskFilter;

@ApplicationScoped
public class TaskJpaRepository implements PanacheRepositoryBase<TaskJpa, String> {

    public List<TaskJpa> list(TaskFilter filter) {
        // No Panache, métodos customizados não são gerados automaticamente.
        // Você implementará sua query aqui (ex: find("title like ?1", "%" + filter.title() + "%").list())
        return List.of();
    }

}
