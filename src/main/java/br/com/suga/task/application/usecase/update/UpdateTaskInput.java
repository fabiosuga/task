package br.com.suga.task.application.usecase.update;

public record UpdateTaskInput(
    String id,
    String title,
    String description,
    boolean completed
) {}
