package com.javasamples.repository;

import com.javasamples.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task save(Task task);

    boolean deleteById(Long id);
}
