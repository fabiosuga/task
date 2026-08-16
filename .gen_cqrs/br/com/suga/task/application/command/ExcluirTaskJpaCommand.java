package br.com.suga.task.application.command;

import java.util.Objects;

public class ExcluirTaskJpaCommand {
    private final String id;

    public ExcluirTaskJpaCommand(String id) {
        this.id = Objects.requireNonNull(id, "id nao pode ser nulo");
    }

    public String getId() {
        return id;
    }
}