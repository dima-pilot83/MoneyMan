package dima.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dima.entity.enums.DoAction;
import dima.entity.enums.TaskStatus;

import javax.persistence.*;

/**
 * Created by Dmitriy on 18.05.2018.
 */
@Entity
@Table(name = "Task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private DoAction action;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "pipeline_Id")
    private Pipeline pipeline;

    public Task() {
    }

    public Task(String name, String description, DoAction action, TaskStatus taskStatus, Pipeline pipeline) {
        this.name = name;
        this.description = description;
        this.action = action;
        this.taskStatus = taskStatus;
        this.pipeline = pipeline;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public DoAction getAction() {
        return action;
    }

    public void setAction(DoAction action) {
        this.action = action;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", action=" + action +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
