package com.javasamples.domain;

import java.time.Instant;

public class Task {

    private final Long id;
    private String title;
    private boolean done;
    private final Instant createdAt;

    public Task(Long id, String title, boolean done, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
