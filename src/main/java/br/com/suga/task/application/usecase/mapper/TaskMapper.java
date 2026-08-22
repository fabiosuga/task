package br.com.suga.task.application.usecase.mapper;

import java.util.UUID;

import br.com.suga.task.application.usecase.insert.InsertTaskInput;
import br.com.suga.task.application.usecase.response.TaskResponse;
import br.com.suga.task.application.usecase.update.UpdateTaskInput;
import br.com.suga.task.domain.model.Task;
import br.com.suga.task.domain.model.vo.TaskDescription;
import br.com.suga.task.domain.model.vo.TaskId;
import br.com.suga.task.domain.model.vo.TaskTitle;

public class TaskMapper {
    
    public Task toInsertDomain(UUID id, InsertTaskInput input) {
        return new Task(
            new TaskId(id.toString()),
            new TaskTitle(input.title()),
            new TaskDescription(input.description()),
            false
        );
    }

    public void toUpdateDomain(Task task, UpdateTaskInput input) {
        task.changeTitle(new TaskTitle(input.title()));
        task.changeDescription(new TaskDescription(input.description()));
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
            task.getId().value(),
            task.getTitle().value(),
            task.getDescription().value(),
            task.isCompleted()
        );
    }
}
