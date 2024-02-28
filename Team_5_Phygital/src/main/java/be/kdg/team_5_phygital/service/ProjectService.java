package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project createProject(Project project);

    Optional<Project> getProjectById(int id);

    List<Project> getAllProjects();
}
