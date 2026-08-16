package br.com.suga.task.domain.repository;

public record TaskFilter(
    String id, 
    String title, 
    String description, 
    Boolean completed) 
{}
