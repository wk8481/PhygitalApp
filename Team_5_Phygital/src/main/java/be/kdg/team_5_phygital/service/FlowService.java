package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Flow;

import java.util.List;
import java.util.Optional;

public interface FlowService {

    Flow createFlow(Flow flow);

    Optional<Flow> getFlowById(int id);

    List<Flow> getAllFlows();
}
