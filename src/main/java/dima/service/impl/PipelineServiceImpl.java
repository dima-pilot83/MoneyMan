package dima.service.impl;

import dima.entity.Pipeline;
import dima.repository.PipelineRepository;
import dima.service.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    private PipelineRepository pipelineRepository;

    @Override
    @Transactional(readOnly = true)
    public Pipeline getPipelineByName(String name) {
        return pipelineRepository.findByLogin(name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return pipelineRepository.existsByLogin(name);
    }

    @Override
    @Transactional
    public void createPipeline(Pipeline pipeline) {
        pipelineRepository.save(pipeline);
    }

    @Override
    @Transactional
    public void updatePipeline(Pipeline pipeline) {
        pipelineRepository.save(pipeline);
    }

    @Override
    @Transactional
    public void deletePipeline(long id) {
        pipelineRepository.delete(id);
    }

    @Override
    public List<Pipeline> getAll() {
        return pipelineRepository.findAll();
    }
}
