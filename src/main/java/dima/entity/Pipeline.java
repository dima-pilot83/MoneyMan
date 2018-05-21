package dima.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dima.entity.enums.PipelineStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 18.05.2018.
 */
@Entity
@Table(name = "Pipeline")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pipeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "pipeline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<Task>();

    @Enumerated(EnumType.STRING)
    private PipelineStatus pipelineStatus;

    @OneToMany(mappedBy = "pipeline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transitions> transitions = new ArrayList<Transitions>();

    @Column(name = "start_time")
    private String startTime;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Pipeline() {
    }

    public Pipeline(String name, String description, List<Task> tasks, PipelineStatus pipelineStatus) {
        this.name = name;
        this.description = description;
        this.tasks = tasks;
        this.pipelineStatus = pipelineStatus;
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

    public List<Task> getTask() {
        return tasks;
    }

    public void setTask(List<Task> task) {
        this.tasks = task;
    }

    public PipelineStatus getPipelineStatus() {
        return pipelineStatus;
    }

    public void setPipelineStatus(PipelineStatus pipelineStatus) {
        this.pipelineStatus = pipelineStatus;
    }

    public List<Transitions> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transitions> transitions) {
        this.transitions = transitions;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Pipeline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", pipelineStatus=" + pipelineStatus +
                ", transitions=" + transitions +
                '}';
    }
}

