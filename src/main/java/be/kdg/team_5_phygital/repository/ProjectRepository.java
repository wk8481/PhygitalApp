package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllBySharingPlatformId(int sharingPlatformId);

    Optional<Project> findByName(String name);

    // find all projects where is public is true
    List<Project> findProjectsByIsPublicIsTrue();

    List<Project> findProjectsByNameLikeIgnoreCase(String searchTerm);

    @Transactional
    @Modifying
    @Query("UPDATE Project e SET e.totalTimeSpentInSec = e.totalTimeSpentInSec + :timeToAdd, e.totalParticipants = e.totalParticipants + 1 WHERE e.id = :projectId")
    void updateProjectTimeAndParticipants(@Param("projectId") int projectId, @Param("timeToAdd") float timeToAdd);



}
