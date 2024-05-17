package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FlowRepository extends JpaRepository<Flow, Integer> {
    List<Flow> findFlowsByProjectIdEquals(int projectId);

    Optional<Flow> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Flow e SET e.totalTimeSpentInSec = e.totalTimeSpentInSec + :timeToAdd, e.totalParticipants = e.totalParticipants + 1 WHERE e.id = :flowId")
    void updateFlowTimeAndParticipants(@Param("flowId") int flowId, @Param("timeToAdd") float timeToAdd);



}
