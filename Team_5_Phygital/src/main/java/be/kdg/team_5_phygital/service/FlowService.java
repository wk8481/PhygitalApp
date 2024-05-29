package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.repository.FlowRepository;
import be.kdg.team_5_phygital.repository.InstallationRepository;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlowService {
    private final FlowRepository flowRepository;
    private final ProjectRepository projectRepository;
    private final InstallationRepository installationRepository;

    public FlowService(FlowRepository flowRepository, ProjectRepository projectRepository, InstallationRepository installationRepository) {
        this.flowRepository = flowRepository;
        this.projectRepository = projectRepository;
        this.installationRepository = installationRepository;
    }

    public Flow getFlow(int flowId) {
        return flowRepository.findById(flowId).orElse(null);
    }

    public List<Flow> getAllFlows() {
        return flowRepository.findAll();
    }

    public List<Flow> getFlowsByProjectId(int projectId) {
        return flowRepository.findFlowsByProjectIdEquals(projectId);
    }

    @Transactional
    public Flow saveFlow(Flow flow) {
        return flowRepository.save(flow);
    }

    @Transactional
    public void updateTimeAndParticipants(Flow flow, float time){

        flowRepository.updateFlowTimeAndParticipants(flow.getId(), time);
    }

    @Transactional
    public boolean updateFlow(int flowId, String name) {
        Flow flow = flowRepository.findById(flowId).orElse(null);
        if (flow == null) {
            return false;
        }
        flow.setName(name);
        flowRepository.save(flow);
        return true;
    }

    @Transactional
    public boolean deleteFlow(int flowId) {
        Optional<Flow> flow = flowRepository.findById(flowId);
        if (flow.isEmpty()) {
            return false;
        }
        flowRepository.deleteById(flowId);
        return true;
    }
}
