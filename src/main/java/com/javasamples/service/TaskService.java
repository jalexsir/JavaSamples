package com.javasamples.service;

import com.javasamples.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findById(Long id);

    Task create(String title);

    Task complete(Long id);

    void delete(Long id);
}
