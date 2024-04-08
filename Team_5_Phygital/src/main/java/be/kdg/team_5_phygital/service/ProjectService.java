package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SharingPlatformRepository sharingPlatformRepository;

    public ProjectService(ProjectRepository projectRepository, SharingPlatformRepository sharingPlatformRepository) {
        this.projectRepository = projectRepository;
        this.sharingPlatformRepository = sharingPlatformRepository;
    }

    public Project getProject(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name).orElse(null);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectBySharingPlatformId(int sharingPlatformId) {
        return projectRepository.findAllBySharingPlatformId(sharingPlatformId);
    }

    @Transactional
    public Project saveProject(String name, int sharingPlatformId) {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        return projectRepository.save(new Project(name, sharingPlatform));
    }

    public boolean updateProject(int projectId, String name) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return false;
        }
        project.setName(name);
        projectRepository.save(project);
        return true;
    }

    @Transactional
    public boolean deleteProject(int projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            return false;
        }
        projectRepository.deleteById(projectId);
        return true;
    }
}
