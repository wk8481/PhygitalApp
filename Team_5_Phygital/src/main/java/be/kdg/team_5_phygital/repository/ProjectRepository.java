package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

    List<Project> getProjectsBySharingPlatform(SharingPlatform sharingPlatform);
}
