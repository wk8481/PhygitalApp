package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.ProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.service.FlowService;
import be.kdg.team_5_phygital.service.ProjectService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectService projectService;
    private final ModelMapper modelMapper;
    private final FlowService flowService;

    public ProjectsController(ProjectService projectService, ModelMapper modelMapper, FlowService flowService) {
        this.projectService = projectService;
        this.modelMapper = modelMapper;
        this.flowService = flowService;
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

    @GetMapping("search")
    ResponseEntity<List<ProjectDto>> searchMenuItems(@RequestParam(required = false) String search) {
        if (search == null || search.trim().isEmpty()) {
            return ResponseEntity.ok(projectService.getAllProjects().stream().map(project -> modelMapper.map(project, ProjectDto.class)).toList());
        } else {
            List<Project> searchResult = projectService.searchProjectsByNameLike(search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult.stream().map(project -> modelMapper.map(project, ProjectDto.class)).toList());
            }
        }
    }

    @PostMapping
    ResponseEntity<ProjectDto> saveProject(@RequestBody @Valid NewProjectDto projectDto) {
        if (projectService.getProjectByName(projectDto.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Creating new project: {}", projectDto.getName());
        Project createdProject = projectService.saveProject(projectDto.getName(), projectDto.getBackgroundColorHex(), projectDto.getFontName(), projectDto.getSharingPlatformId());
        flowService.saveFlow(new Flow("flow 1", true, createdProject));
        flowService.saveFlow(new Flow("flow 2", false, createdProject));
        return new ResponseEntity<>(modelMapper.map(createdProject, ProjectDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{projectId}")
    ResponseEntity<Void> updateProject(@PathVariable int projectId, @Valid UpdateProjectDto updateProjectDto, @RequestParam(value = "logo", required = false) MultipartFile logoFile) throws IOException {
        if (projectService.updateProject(projectId, updateProjectDto, logoFile)) {
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
