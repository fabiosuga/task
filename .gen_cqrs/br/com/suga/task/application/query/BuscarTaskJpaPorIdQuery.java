package br.com.suga.task.application.query;

import java.util.Objects;

public class BuscarTaskJpaPorIdQuery {
    private final String id;

    public BuscarTaskJpaPorIdQuery(String id) {
        this.id = Objects.requireNonNull(id, "id nao pode ser nulo");
    }

    public String getId() {
        return id;
    }
}