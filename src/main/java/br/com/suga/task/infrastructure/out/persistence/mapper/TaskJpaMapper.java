package br.com.suga.task.infrastructure.out.persistence.mapper;

import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.model.vo.TaskDescription;
import br.com.suga.task.domain.model.vo.TaskId;
import br.com.suga.task.domain.model.vo.TaskTitle;
import br.com.suga.task.infrastructure.out.persistence.TaskJpa;

public class TaskJpaMapper {

    public static TaskJpa toJpa(Task task) {
        return new TaskJpa(
            task.getId().value(),
            task.getTitle().value(),
            task.getDescription().value(),
            task.isCompleted()
        );
    }

    public static Task toDomain(TaskJpa taskJpa) {
        return new Task(
            new TaskId(taskJpa.getId()),
            new TaskTitle(taskJpa.getTitle()),
            new TaskDescription(taskJpa.getDescription()),
            taskJpa.isCompleted()
        );
    }
}
