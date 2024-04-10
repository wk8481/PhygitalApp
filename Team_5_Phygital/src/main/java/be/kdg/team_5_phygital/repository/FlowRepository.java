package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlowRepository extends JpaRepository<Flow, Integer> {
    List<Flow> findFlowsByProjectIdEquals(int projectId);

    Optional<Flow> findByName(String name);



}
