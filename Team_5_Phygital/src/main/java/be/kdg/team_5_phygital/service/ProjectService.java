package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService{
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById(int id) {
        return projectRepository.findById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectBySharingPlatform(SharingPlatform sharingPlatform){return projectRepository.getProjectsBySharingPlatform(sharingPlatform);}

    public boolean updateProject(int projectId, String name) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return false;
        }
        project.setName(name);
        projectRepository.save(project);
        return true;
    }
}
