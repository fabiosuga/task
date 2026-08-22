package br.com.suga.task.infrastructure.in.rest;

import jakarta.ws.rs.QueryParam;

public class FilterTaskRequest {

    @QueryParam("id")
    private String id;

    @QueryParam("title")
    private String title;

    @QueryParam("description")
    private String description;

    @QueryParam("completed")
    private Boolean completed;

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public Boolean completed() {
        return completed;
    }
}
