package dima.service.impl;

import dima.entity.Task;
import dima.entity.enums.PipelineStatus;
import dima.entity.enums.TaskStatus;
import dima.repository.TaskRepository;
import dima.service.ActionService;
import dima.service.PipelineService;
import dima.service.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dmitriy on 18.05.2018.
 */
@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private PipelineService pipelineService;

    private static final Logger log = Logger.getLogger(ActionServiceImpl.class);
    Date dd = new Date();
    SimpleDateFormat stf = new SimpleDateFormat("yy:MM:dd: HH:mm:ss");

    @Override
    public synchronized void execution(String action, String taskName) {
        if (action.equals("PRINT")) {
            print(taskName);
        }
        if (action.equals("RANDOM")) {
            random(taskName);
        }
        if (action.equals("COMPLETED")) {
            completed(taskName);
        }
        if (action.equals("DELAYED")) {
            completed(taskName);
        }
    }

    @Override
    public void print(String name) {
        log.info("Start time " + stf.format(dd));
        Task task = taskRepository.findByName(name);
        System.out.println(task.getName());
        task.setTaskStatus(TaskStatus.COMPLETED);
        taskService.updateTask(task);
        log.info("name " + task.getName());
        log.info("End time " + stf.format(dd));
        log.info("status " + task.getTaskStatus());

    }

    @Override
    public void random(String name) {
        log.info("Start time " + stf.format(dd));
        Task task = taskRepository.findByName(name);
        System.out.println(task.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setTaskStatus(TaskStatus.getRandom());
        taskService.updateTask(task);
        if (task.getTaskStatus().equals(TaskStatus.FAILED)) {
           task.getPipeline().setPipelineStatus(PipelineStatus.FAILED);
            //break pipeline in controller
            log.info("name " + task.getName());
            log.info("End time " + stf.format(dd));
            log.info("status " + task.getTaskStatus());
        }
        log.info("name " + task.getName());
        log.info("End time " + stf.format(dd));
        log.info("status " + task.getTaskStatus());
    }

    @Override
    public void completed(String name) {
        log.info("Start time " + stf.format(dd));
        Task task = taskRepository.findByName(name);
        System.out.println(task.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setTaskStatus(TaskStatus.COMPLETED);
        taskService.updateTask(task);
        log.info("name " + task.getName());
        log.info("End time " + stf.format(dd));
        log.info("status " + task.getTaskStatus());
    }

    @Override
    public void delayed(String name) {
        log.info("Start time " + stf.format(dd));
        Task task = taskRepository.findByName(name);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setTaskStatus(TaskStatus.COMPLETED);
        taskService.updateTask(task);
        log.info("name " + task.getName());
        log.info("End time " + stf.format(dd));
        log.info("status " + task.getTaskStatus());
    }
}
