package br.com.suga.task.application.usecase.list;

public record FilterTaskInput(
    String id,
    String title,
    String description,
    Boolean completed
) {}
