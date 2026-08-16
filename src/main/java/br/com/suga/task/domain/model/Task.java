package br.com.suga.task.domain.model;

import br.com.suga.task.domain.model.vo.TaskDescription;
import br.com.suga.task.domain.model.vo.TaskId;
import br.com.suga.task.domain.model.vo.TaskTitle;

/**
 * Using a DDD approach, this class represents a Task entity in the domain layer. It encapsulates the properties and behaviors of a task, including its unique identifier (TaskId), title (TaskTitle), description (TaskDescription), and completion status (completed).
 * The Task class is designed to enforce domain rules and constraints through the use of value objects for
 * Task
 */
public class Task {

    private TaskId id;
    private TaskTitle title;
    private TaskDescription description;
    private boolean completed;

    public Task(TaskTitle title, TaskDescription description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Task(TaskId id, TaskTitle title, TaskDescription description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public TaskId getId() {
        return id;
    }

    public TaskTitle getTitle() {
        return title;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void changeTitle(TaskTitle title) {
        this.title = title;
    }

    public void changeDescription(TaskDescription description) {
        this.description = description;
    }

    public void completeTask() {
        this.completed = true;
    }

    public void uncompleteTask() {
        this.completed = false;
    }
}
