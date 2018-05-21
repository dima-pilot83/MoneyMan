package dima.service;

import dima.entity.Pipeline;


import java.util.List;

public interface PipelineService {
    Pipeline getPipelineByName(String name);
    boolean existsByName(String name);
    void deletePipeline(long id);
    void createPipeline(Pipeline pipeline);
    void updatePipeline(Pipeline pipeline);
    List<Pipeline> getAll();
}
