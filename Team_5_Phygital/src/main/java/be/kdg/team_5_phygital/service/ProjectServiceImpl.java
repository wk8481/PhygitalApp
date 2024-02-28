package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.repository.ProjectRepo;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService{
    private ProjectRepo projectRepository;

    public ProjectServiceImpl(ProjectRepo projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getProjectById(int id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
