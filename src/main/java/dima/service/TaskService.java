package dima.service;

import dima.entity.Task;

import java.util.List;


public interface TaskService {
    Task getTaskByName(String name);
    void deleteTask(long id);
    Task createTask(Task task);
    Task updateTask(Task task);
    List<Task> getAll();
}
