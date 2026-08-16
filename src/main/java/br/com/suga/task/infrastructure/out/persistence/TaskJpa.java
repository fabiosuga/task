package br.com.suga.task.infrastructure.out.persistence;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "task", name = "tb_tasks")
public class TaskJpa {
    
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

    public TaskJpa() {}

    public TaskJpa(String id, String title, String description, boolean completed) {
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

    public boolean isCompleted() {
        return completed;
    }

}
