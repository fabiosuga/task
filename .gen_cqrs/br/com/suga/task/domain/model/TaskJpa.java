package br.com.suga.task.domain.model;

import java.util.Objects;

public class TaskJpa {
    private final String id;
    private final String title;
    private final String description;
    private final boolean completed;

    public TaskJpa(String id, String title, String description, boolean completed) {
        this.id = Objects.requireNonNull(id, "id nao pode ser nulo");
        this.title = Objects.requireNonNull(title, "title nao pode ser nulo");
        this.description = Objects.requireNonNull(description, "description nao pode ser nulo");
        this.completed = completed;
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