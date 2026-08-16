package br.com.suga.task.application.dto;

public class CriarTaskJpaRequest {
    private final String id;
    private final String title;
    private final String description;
    private final boolean completed;

    public CriarTaskJpaRequest(String id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
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
