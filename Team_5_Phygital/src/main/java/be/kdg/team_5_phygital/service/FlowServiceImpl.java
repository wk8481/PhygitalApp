package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.repository.FlowRepo;

import java.util.List;
import java.util.Optional;

public class FlowServiceImpl implements FlowService{
    private FlowRepo flowRepository;

    public FlowServiceImpl(FlowRepo flowRepository) {
        this.flowRepository = flowRepository;
    }

    @Override
    public Flow createFlow(Flow flow) {
        return flowRepository.save(flow);
    }

    @Override
    public Optional<Flow> getFlowById(int id) {
        return flowRepository.findById(id);
    }

    @Override
    public List<Flow> getAllFlows() {
        return flowRepository.findAll();
    }
}
