package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateQuestionDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSubthemeDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSupervisorDto;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.service.ProjectService;
import be.kdg.team_5_phygital.service.QuestionService;
import be.kdg.team_5_phygital.service.SubThemeService;
import be.kdg.team_5_phygital.service.SupervisorService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sharing-platform")
public class RESTSharingPlatformController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectService projectService;
    private final SupervisorService superVisorService;
    private final SubThemeService subThemeService;
    private final QuestionService questionService;

    public RESTSharingPlatformController(ProjectService projectService, SupervisorService superVisorService, SubThemeService subThemeService, QuestionService questionService) {
        this.projectService = projectService;
        this.superVisorService = superVisorService;
        this.subThemeService = subThemeService;
        this.questionService = questionService;
    }

    @Transactional
    @PatchMapping("/platform/{platformId}/project/{projectId}/update")
    public Project updateProject(@PathVariable int platformId, @PathVariable int projectId, @RequestBody UpdateProjectDto updatedProject) {
        Project original = projectService.getProjectById(projectId).orElse(null);
        Project updated = new Project(projectId, updatedProject.getName(), original.getTheme(), original.isActive(), original.getTotalParticipants(), original.getAvgTimeSpent(), original.getSupervisor(), original.getInstallation(), original.getFlows(), original.getSharingPlatform(), original.getAdministrator());
        return projectService.updateProject(updated);
        }

    @Transactional
    @PatchMapping("/platform/{platformId}/supervisor/{superVisorId}/update")
    public Supervisor updateSuperVisorName(@PathVariable int superVisorId, @RequestBody UpdateSupervisorDto updatedSupervisor){
        Supervisor original = superVisorService.getSupervisorById(superVisorId).orElse(null);
        Supervisor updated = new Supervisor(superVisorId, updatedSupervisor.getName(), updatedSupervisor.getEmail(), original.getPassword(), original.getSharingPlatform(), original.getProjects());
        return superVisorService.updateSupervisor(updated);
    }

    @Transactional
    @PatchMapping("/flow/{flowId}/sub-theme/{stId}/update")
    public SubTheme updateSubtheme(@PathVariable int stId, @RequestBody UpdateSubthemeDto updateSubthemeDto){
        SubTheme original = subThemeService.getSubthemeById(stId).orElse(null);
        SubTheme updated = new SubTheme(stId, updateSubthemeDto.getName(), updateSubthemeDto.getInformation(), original.getFlow());
        return subThemeService.updateSubTheme(updated);
    }

    @Transactional
    @PatchMapping("/sub-theme/{stId}/question/{qId}/update")
    public Question updateQuestion(@PathVariable int qId, @RequestBody UpdateQuestionDto updateQuestionDto){
        Question original = questionService.getQuestionById(qId).orElse(null);
        Question updated = new Question(qId, updateQuestionDto.getText(), updateQuestionDto.getType(), original.getSubTheme());
        return questionService.updateQuestion(updated);
    }

}
