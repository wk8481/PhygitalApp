package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllBySharingPlatformId(int sharingPlatformId);

    Optional<Project> findByName(String name);

    List<Project> findProjectsByNameLikeIgnoreCase(String searchTerm);

}
