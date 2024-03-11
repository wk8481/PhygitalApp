package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.repository.FlowRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowService {
    private FlowRepo flowRepository;

    public FlowService(FlowRepo flowRepository) {
        this.flowRepository = flowRepository;
    }


    public Flow createFlow(Flow flow) {
        return flowRepository.save(flow);
    }


    public Optional<Flow> getFlowById(int id) {
        return flowRepository.findById(id);
    }


    public List<Flow> getAllFlows() {
        return flowRepository.findAll();
    }
}
