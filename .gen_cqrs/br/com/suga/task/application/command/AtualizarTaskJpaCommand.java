package br.com.suga.task.application.command;

import java.util.Objects;

public class AtualizarTaskJpaCommand {
    private final String id;
    private final String title;
    private final String description;
    private final boolean completed;

    public AtualizarTaskJpaCommand(String id, String title, String description, boolean completed) {
        Objects.requireNonNull(id, "id nao pode ser nulo");
        this.id = id;
        Objects.requireNonNull(title, "title nao pode ser nulo");
        this.title = title;
        Objects.requireNonNull(description, "description nao pode ser nulo");
        this.description = description;
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