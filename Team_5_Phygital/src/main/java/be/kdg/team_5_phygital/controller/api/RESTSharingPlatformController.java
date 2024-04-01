package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateQuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSubthemeDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSupervisorDto;
import be.kdg.team_5_phygital.service.ProjectService;
import be.kdg.team_5_phygital.service.QuestionService;
import be.kdg.team_5_phygital.service.SubThemeService;
import be.kdg.team_5_phygital.service.SupervisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sharing-platform")
public class RESTSharingPlatformController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectService projectService;
    private final SupervisorService supervisorService;
    private final SubThemeService subThemeService;
    private final QuestionService questionService;

    public RESTSharingPlatformController(ProjectService projectService, SupervisorService supervisorService, SubThemeService subThemeService, QuestionService questionService) {
        this.projectService = projectService;
        this.supervisorService = supervisorService;
        this.subThemeService = subThemeService;
        this.questionService = questionService;
    }

    @PatchMapping("/platform/{platformId}/project/{projectId}/update")
    ResponseEntity<Void> updateProject(@PathVariable int platformId, @PathVariable int projectId, @RequestBody UpdateProjectDto updateProject) {
        if (projectService.updateProject(projectId, updateProject.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/platform/{platformId}/supervisor/{supervisorId}/update")
    ResponseEntity<Void> updateSupervisorName(@PathVariable int supervisorId, @RequestBody UpdateSupervisorDto updateSupervisorDto) {
        if (supervisorService.updateSupervisor(supervisorId, updateSupervisorDto.getName(), updateSupervisorDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/flow/{flowId}/sub-theme/{subThemeId}/update")
    ResponseEntity<Void> updateSubtheme(@PathVariable int subThemeId, @RequestBody UpdateSubthemeDto updateSubthemeDto) {
        if (subThemeService.updateSubTheme(subThemeId, updateSubthemeDto.getName(), updateSubthemeDto.getInformation())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/sub-theme/{stId}/question/{questionId}/update")
    ResponseEntity<Void> updateQuestion(@PathVariable int questionId, @RequestBody UpdateQuestionDto updateQuestionDto) {
        if (questionService.updateQuestion(questionId, updateQuestionDto.getText(), updateQuestionDto.getType())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
