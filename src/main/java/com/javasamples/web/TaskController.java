package com.javasamples.web;

import com.javasamples.domain.Task;
import com.javasamples.dto.TaskRequest;
import com.javasamples.dto.TaskResponse;
import com.javasamples.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> findAll() {
        return taskService.findAll().stream()
                .map(TaskResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return TaskResponse.from(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request) {
        Task created = taskService.create(request.title());
        TaskResponse body = TaskResponse.from(created);
        return ResponseEntity.created(URI.create("/api/tasks/" + created.getId())).body(body);
    }

    @PostMapping("/{id}/complete")
    public TaskResponse complete(@PathVariable Long id) {
        return TaskResponse.from(taskService.complete(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
