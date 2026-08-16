package br.com.suga.task.infrastructure.out.persistence.entity;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(schema = "task", name = "tb_tasks")
public class TaskJpaJpaEntity {
    @Id
    private String id;

    @Column(name = "title", nullable = false, length = 100)
    @Length(min = 3, max = 100, message = "Task title cannot exceed 100 characters")
    private String title;

    @Column(name = "description", length = 500)
    @Length(min = 5, max = 500, message = "Task description cannot exceed 500 characters")
    private String description;

    @Column(name = "completed", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean completed;

    public TaskJpaJpaEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}