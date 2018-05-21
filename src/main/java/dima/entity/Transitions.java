package dima.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dmitriy on 19.05.2018.
 */
@Entity
@Table(name = "Transitions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transitions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "from")
    private String from;

    @Column(name = "to")
    private String to;

    @ManyToOne
    @JoinColumn(name = "pipeline_Id")
    private Pipeline pipeline;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public String toString() {
        return "Transitions{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", pipeline=" + pipeline +
                '}';
    }
}
