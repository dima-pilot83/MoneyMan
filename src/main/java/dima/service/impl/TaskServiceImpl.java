package dima.service.impl;

import dima.entity.Task;
import dima.repository.TaskRepository;
import dima.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dmitriy on 18.05.2018.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getTaskByName(String name) {
        return taskRepository.findByName(name);
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        taskRepository.saveAndFlush(task);
        return task;
    }

    @Override
    @Transactional
    public Task updateTask(Task task) {
        taskRepository.saveAndFlush(task);
        return task;
    }

    @Override
    @Transactional
    public void deleteTask(long id) {
        taskRepository.delete(id);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

}
