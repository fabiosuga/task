package br.com.suga.task.application.usecase.response;

public record TaskResponse(
    String id, 
    String title, 
    String description, 
    boolean completed) 
{}
