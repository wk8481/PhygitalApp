package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.ProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.service.ProjectService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectService projectService;
    private final ModelMapper modelMapper;

    public ProjectsController(ProjectService projectService, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<ProjectDto> getProject(@PathVariable("id") int projectId) {
        Project project = projectService.getProject(projectId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(project, ProjectDto.class));
    }

    @GetMapping
    ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<Project> allProjects = projectService.getAllProjects();
        if (allProjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ProjectDto> projectDtos = allProjects.stream().map(project -> modelMapper.map(project, ProjectDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(projectDtos);
        }
    }

    @PostMapping
    ResponseEntity<ProjectDto> saveProject(@RequestBody @Valid NewProjectDto projectDto) {
        if (projectService.getProjectByName(projectDto.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Creating new project: {}", projectDto.getName());
        Project createdProject = projectService.saveProject(projectDto.getName(), projectDto.getBackgroundColorHex(), projectDto.getFontName(), projectDto.getLogoPath(), projectDto.getSharingPlatformId());
        return new ResponseEntity<>(modelMapper.map(createdProject, ProjectDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{projectId}")
    ResponseEntity<Void> updateProject(@PathVariable int projectId, @RequestBody UpdateProjectDto updateProjectDto) {
        if (projectService.updateProject(projectId, updateProjectDto.getName(), updateProjectDto.getBackgroundColorHex(), updateProjectDto.getFontName(), updateProjectDto.getLogoPath())) {
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
