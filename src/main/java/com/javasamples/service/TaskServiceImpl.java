package com.javasamples.service;

import com.javasamples.domain.Task;
import com.javasamples.exception.TaskNotFoundException;
import com.javasamples.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final Clock clock;

    public TaskServiceImpl(TaskRepository taskRepository, Clock clock) {
        this.taskRepository = taskRepository;
        this.clock = clock;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task create(String title) {
        Task task = new Task(null, title, false, clock.instant());
        return taskRepository.save(task);
    }

    @Override
    public Task complete(Long id) {
        Task task = findById(id);
        task.setDone(true);
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.deleteById(id)) {
            throw new TaskNotFoundException(id);
        }
    }
}
