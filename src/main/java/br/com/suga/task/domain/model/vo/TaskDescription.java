package br.com.suga.task.domain.model.vo;

public record TaskDescription(String value) {
    public TaskDescription {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be null or blank");
        }

        if (value.length() < 5) {
            throw new IllegalArgumentException("Task description must be at least 5 characters long");
        }

        if (value.length() > 500) {
            throw new IllegalArgumentException("Task description cannot exceed 500 characters");
        }
    }
}
