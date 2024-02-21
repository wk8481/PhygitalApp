package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Integer>{
}
