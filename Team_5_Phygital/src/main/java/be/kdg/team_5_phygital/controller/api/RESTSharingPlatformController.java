package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sharing-platform")
public class RESTSharingPlatformController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectService projectService;


    public RESTSharingPlatformController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PatchMapping("{platformId}/project/{projectId}/update")
    public String updateProject(@PathVariable int platformId, @PathVariable int projectId, @RequestBody UpdateProjectDto updatedProject) {
        projectService.updateProject(new Project(updatedProject.getId(), updatedProject.getName()));
        return null;
        }
    }
