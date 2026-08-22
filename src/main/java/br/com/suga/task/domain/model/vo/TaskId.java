package br.com.suga.task.domain.model.vo;

public record TaskId(String value) {
    public TaskId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Task ID cannot be null or blank");
        }
    }
}
