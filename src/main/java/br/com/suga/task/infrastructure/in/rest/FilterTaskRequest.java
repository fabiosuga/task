package br.com.suga.task.infrastructure.in.rest;

public record FilterTaskRequest(
    String id, 
    String title, 
    String description, 
    Boolean completed) 
{}
