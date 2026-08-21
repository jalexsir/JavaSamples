package com.javasamples.dto;

import com.javasamples.domain.Task;

import java.time.Instant;

public record TaskResponse(Long id, String title, boolean done, Instant createdAt) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.isDone(), task.getCreatedAt());
    }
}
