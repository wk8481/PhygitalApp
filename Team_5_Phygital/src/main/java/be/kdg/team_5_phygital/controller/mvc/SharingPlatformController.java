package be.kdg.team_5_phygital.controller.mvc;

import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("sharing-platform")
public class SharingPlatformController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SharingPlatformService sharingPlatformService;
    private final ProjectService projectService;
    private final SupervisorService supervisorService;
    private final ThemeService themeService;
    private final FlowService flowService;
    private final SubThemeService subThemeService;
    private final QuestionService questionService;

    public SharingPlatformController(SharingPlatformService sharingPlatformService, ProjectService projectService, SupervisorService supervisorService, ThemeService themeService, FlowService flowService, SubThemeService subThemeService, QuestionService questionService) {
        this.sharingPlatformService = sharingPlatformService;
        this.projectService = projectService;
        this.supervisorService = supervisorService;
        this.themeService = themeService;
        this.flowService = flowService;
        this.subThemeService = subThemeService;
        this.questionService = questionService;
    }

    @GetMapping("login")
    public String getLogin() {
        return "sharing-platform/login";
    }

    @GetMapping("dashboard/{id}")
    public String getDashboard(@PathVariable int id, Model model) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatformById(id).orElse(null);
        List<Project> projectList = projectService.getProjectBySharingPlatform(sharingPlatform);

        List<Supervisor> supervisorList = supervisorService.findSupervisorBySharingPlatform(sharingPlatform);

        model.addAttribute("platform", sharingPlatform);
        model.addAttribute("projects", projectList);
        model.addAttribute("supervisor", supervisorList);
        return "sharing-platform/dashboard";
    }


    @GetMapping("{platformId}/project/{projectId}")
    public String getProject(@PathVariable int platformId, @PathVariable int projectId, Model model) {
        Project project = projectService.getProjectById(projectId).orElse(null);
        List<Flow> flows = flowService.getFlowsByProjectId(project);
        model.addAttribute("project", project);
        model.addAttribute("flow", flows);
        return "sharing-platform/project";
    }

    @GetMapping("project/{projectId}/theme")
    public String getTheme(@PathVariable int projectId, Model model) {
        Theme theme = themeService.getThemeByProjectId(projectId);
        model.addAttribute("theme", theme);
        return "sharing-platform/theme";
    }

    @GetMapping("project/{projectId}/flow/{flowId}")
    public String getFlow(@PathVariable int flowId, Model model) {
        Flow flow = flowService.getFlowById(flowId).orElse(null);
        List<SubTheme> subThemes = subThemeService.getSubthemeByFlowId(flow);
        model.addAttribute("flow", flow);
        model.addAttribute("st", subThemes);
        return "sharing-platform/flow";
    }

    @GetMapping("flow/{flowId}/sub-theme/{stId}")
    public String getSubTheme(@PathVariable int stId, Model model) {
        SubTheme subTheme = subThemeService.getSubthemeById(stId).orElse(null);
        List<Question> questions = questionService.getQuestionBySubTheme(subTheme);
        model.addAttribute("st", subTheme);
        model.addAttribute("question", questions);
        return "sharing-platform/sub-theme";
    }

    @GetMapping("sub-theme/{stId}/question/{questionId}")
    public String getQuestion(@PathVariable int questionId, Model model) {
        Question question = questionService.getQuestionById(questionId).orElse(null);
        model.addAttribute("q", question);
        return "sharing-platform/question";
    }

    @GetMapping("{platformId}/supervisor/{supervisorId}")
    public String getSupervisor(@PathVariable int platformId, @PathVariable int supervisorId, Model model) {
        model.addAttribute("sv", supervisorService.getSupervisorById(supervisorId).orElse(null));
        return "sharing-platform/supervisor";
    }

    @GetMapping("project/{projectId}/stats")
    public String getProjectStats(@PathVariable int projectId, Model model) {
        Project project = projectService.getProjectById(projectId).orElse(null);
        model.addAttribute("project", project);
        return "sharing-platform/project-stats";
    }
}
