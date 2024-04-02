package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.repository.FlowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowService {
    private FlowRepository flowRepository;

    public FlowService(FlowRepository flowRepository) {
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

    public List<Flow> getFlowsByProjectId(Project project){ return flowRepository.findFlowsByProjectEquals(project);}

    @Transactional
    public Flow saveFlow(String name) {
        return flowRepository.save(new Flow(name));
    }

    public boolean updateFlow(int flowId, String name) {
        Flow flow = flowRepository.findById(flowId).orElse(null);
        if (flow == null) {
            return false;
        }
        flow.setName(name);
        flowRepository.save(flow);
        return true;
    }
}
