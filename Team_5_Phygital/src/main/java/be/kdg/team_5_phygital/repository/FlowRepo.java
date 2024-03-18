package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowRepo extends JpaRepository<Flow, Integer> {

        List<Flow> findFlowsByProjectEquals(Project project);
}
