package com.niit.mongodpractice.service;

import com.niit.mongodpractice.domain.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task getTaskById(String id);
    Task updateTask(String id, Task updatedTask);
    List<Task> getTasksByStatus(String status);
}