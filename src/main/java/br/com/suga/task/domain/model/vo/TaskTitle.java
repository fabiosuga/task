package br.com.suga.task.domain.model.vo;

public record TaskTitle(String value) {
    public TaskTitle {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or blank");
        }

        if (value.length() < 3) {
            throw new IllegalArgumentException("Task title must be at least 3 characters long");
        }

        if (value.length() > 100) {
            throw new IllegalArgumentException("Task title cannot exceed 100 characters");
        }
    }
}
