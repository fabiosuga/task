package br.com.suga.task.infrastructure.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.repository.TaskFilter;

public interface TaskJpaRepository extends JpaRepository<Task, String> {

    List<Task> list(TaskFilter filter);

}
