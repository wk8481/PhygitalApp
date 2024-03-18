package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.repository.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService{
    private final ProjectRepo projectRepository;

    public ProjectService(ProjectRepo projectRepository) {
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

    public Project updateProject(Project updatedProject){ return projectRepository.save(updatedProject);}
}
