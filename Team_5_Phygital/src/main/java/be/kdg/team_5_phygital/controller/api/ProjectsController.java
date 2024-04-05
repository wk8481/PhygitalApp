package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.ProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import be.kdg.team_5_phygital.service.ProjectService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectsController(ProjectService projectService, ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<ProjectDto> saveProject(@RequestBody @Valid NewProjectDto projectDto) {
        if (projectRepository.findByName(projectDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Project createdProject = projectService.saveProject(projectDto.getName(), projectDto.getSharingPlatformId());
        return new ResponseEntity<>(modelMapper.map(createdProject, ProjectDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{projectId}")
    ResponseEntity<Void> updateProject(@PathVariable int projectId, @RequestBody UpdateProjectDto updateProject) {
        if (projectService.updateProject(projectId, updateProject.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{projectId}")
    ResponseEntity<Void> deleteProject(@PathVariable("projectId") int projectId) {
        if (projectService.deleteProject(projectId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
