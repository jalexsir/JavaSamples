package com.javasamples.service;

import com.javasamples.domain.Task;
import com.javasamples.exception.TaskNotFoundException;
import com.javasamples.repository.InMemoryTaskRepository;
import com.javasamples.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskServiceImplTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = new InMemoryTaskRepository();
        Clock fixedClock = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
        taskService = new TaskServiceImpl(taskRepository, fixedClock);
    }

    @Test
    void createsTaskAndAssignsId() {
        Task task = taskService.create("Learn Spring internals");

        assertThat(task.getId()).isNotNull();
        assertThat(task.getTitle()).isEqualTo("Learn Spring internals");
        assertThat(task.isDone()).isFalse();
    }

    @Test
    void completesExistingTask() {
        Task created = taskService.create("Learn DI");

        Task completed = taskService.complete(created.getId());

        assertThat(completed.isDone()).isTrue();
    }

    @Test
    void throwsWhenTaskMissing() {
        assertThatThrownBy(() -> taskService.findById(999L))
                .isInstanceOf(TaskNotFoundException.class);
    }
}
