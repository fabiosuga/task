package br.com.suga.task.infrastructure.in.rest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
    @NotEmpty(message = "Id is required")
    String id, 
    
    @NotEmpty(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    String title, 
    
    @NotEmpty(message = "Description is required")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    String description, 
    boolean completed)
{}
