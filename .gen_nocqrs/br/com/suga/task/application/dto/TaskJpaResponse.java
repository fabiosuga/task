package br.com.suga.task.application.dto;

import br.com.suga.task.domain.model.TaskJpa;

public class TaskJpaResponse {
    private final String id;
    private final String title;
    private final String description;
    private final boolean completed;

    public TaskJpaResponse(String id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public static TaskJpaResponse fromDomain(TaskJpa domain) {
        return new TaskJpaResponse(domain.getId(), domain.getTitle(), domain.getDescription(), domain.getCompleted());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompleted() {
        return completed;
    }

}
