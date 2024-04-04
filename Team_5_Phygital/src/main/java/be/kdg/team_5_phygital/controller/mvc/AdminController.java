package be.kdg.team_5_phygital.controller.mvc;

import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final SharingPlatformService sharingPlatformService;
    private final SharingPlatformAdminService sharingPlatformAdminService;
    private final ProjectService projectService;
    private final SupervisorService supervisorService;
    private final ThemeService themeService;
    private final FlowService flowService;
    private final SubThemeService subThemeService;
    private final QuestionService questionService;

    public AdminController(SharingPlatformService sharingPlatformService, SharingPlatformAdminService sharingPlatformAdminService, ProjectService projectService, SupervisorService supervisorService, ThemeService themeService, FlowService flowService, SubThemeService subThemeService, QuestionService questionService) {
        this.sharingPlatformService = sharingPlatformService;
        this.sharingPlatformAdminService = sharingPlatformAdminService;
        this.projectService = projectService;
        this.supervisorService = supervisorService;
        this.themeService = themeService;
        this.flowService = flowService;
        this.subThemeService = subThemeService;
        this.questionService = questionService;
    }

    @GetMapping("platform")
    public String getPlatform(Model model) {
        List<SharingPlatform> platformList = sharingPlatformService.getAllSharingPlatforms();
        model.addAttribute("platforms", platformList);
        return "admin/platform";
    }


    @GetMapping("sharing-platform/{platformId}")
    public String getSharingPlatform(@PathVariable int platformId, Model model) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatformById(platformId).orElse(null);
        SharingPlatformAdmin client = sharingPlatformAdminService.findAllSharingPlatformAdmins().get(0);
        List<Project> projectList = projectService.getProjectBySharingPlatform(sharingPlatform);
        List<Supervisor> supervisorList = supervisorService.findSupervisorBySharingPlatform(sharingPlatform);
        model.addAttribute("platform", sharingPlatform);
        model.addAttribute("client", client);
        model.addAttribute("projects", projectList);
        model.addAttribute("supervisor", supervisorList);
        return "admin/sharing-platform";
    }

    @GetMapping("sharing-platform/new")
    public String getNewSharingPlatform() {
        return "admin/new-sharing-platform";
    }

    @GetMapping("sharing-platform/{platformId}/client/{clientId}")
    public String getClient(@PathVariable int platformId, @PathVariable int clientId, Model model) {
        SharingPlatformAdmin client = sharingPlatformAdminService.findAllSharingPlatformAdmins().get(0);
        model.addAttribute("client", client);
        return "admin/client";
    }

    @GetMapping("sharing-platform/{platformId}/stats")
    public String getPlatformStats(@PathVariable int platformId, Model model) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatformById(platformId).orElse(null);
        Project project = projectService.getAllProjects().stream().findFirst().orElse(null);
        model.addAttribute("platform", sharingPlatform);
        model.addAttribute("project", project);
        return "admin/sharing-platform-stats";
    }

    @GetMapping("{platformId}/project/{projectId}")
    public String getProject(@PathVariable int platformId, @PathVariable int projectId, Model model) {
        Project project = projectService.getProjectById(projectId).orElse(null);
        List<Flow> flows = flowService.getFlowsByProjectId(project);
        model.addAttribute("project", project);
        model.addAttribute("flow", flows);
        return "admin/project";
    }

    @GetMapping("{platformId}/project/new")
    public String getNewProject(@PathVariable int platformId) {
        return "admin/new-project";
    }

    @GetMapping("project/{projectId}/theme")
    public String getTheme(@PathVariable int projectId, Model model) {
        Theme theme = themeService.getThemeByProjectId(projectId);
        model.addAttribute("theme", theme);
        return "admin/theme";
    }

    @GetMapping("sharing-platform/{platformId}/{projectId}/stats")
    public String getProjectStats(@PathVariable int platformId, @PathVariable int projectId) {
        return "admin/project-stats";
    }

    @GetMapping("project/{projectId}/flow/{flowId}")
    public String getFlow(@PathVariable int projectId, @PathVariable int flowId, Model model) {
        Flow flow = flowService.getFlowById(flowId).orElse(null);
        List<SubTheme> subThemes = subThemeService.getSubthemeByFlowId(flow);
        model.addAttribute("flow", flow);
        model.addAttribute("st", subThemes);
        return "admin/flow";
    }

    @GetMapping("project/{projectId}/flow/new")
    public String getNewFlow(@PathVariable int projectId) {
        return "admin/new-flow";
    }

    @GetMapping("flow/{flowId}/sub-theme/{subThemeId}")
    public String getSubTheme(@PathVariable int flowId, @PathVariable int subThemeId, Model model) {
        SubTheme subTheme = subThemeService.getSubthemeById(subThemeId).orElse(null);
        List<Question> questions = questionService.getQuestionBySubTheme(subTheme);
        model.addAttribute("st", subTheme);
        model.addAttribute("question", questions);
        return "admin/sub-theme";
    }

    @GetMapping("flow/{flowId}/sub-theme/new")
    public String getNewSubTheme(@PathVariable int flowId) {
        return "admin/new-sub-theme";
    }

    @GetMapping("sub-theme/{stId}/question/{questionId}")
    public String getQuestion(@PathVariable int questionId, Model model) {
        Question question = questionService.getQuestionById(questionId).orElse(null);
        model.addAttribute("q", question);
        return "admin/question";
    }

    @GetMapping("sub-theme/{subThemeId}/question/new")
    public String getNewQuestion(@PathVariable int subThemeId) {
        return "admin/new-question";
    }

    @GetMapping("{platformId}/supervisor/{supervisorId}")
    public String getSupervisor(@PathVariable int platformId, @PathVariable int supervisorId, Model model) {
        model.addAttribute("sv", supervisorService.getSupervisorById(supervisorId).orElse(null));
        return "admin/supervisor";
    }

    @GetMapping("{platformId}/supervisor/new")
    public String getNewSupervisor(@PathVariable int platformId) {
        return "admin/new-supervisor";
    }

    @GetMapping("project/{projectId}/stats")
    public String getProjectStats(@PathVariable int projectId, Model model) {
        Project project = projectService.getProjectById(projectId).orElse(null);
        model.addAttribute("project", project);
        return "admin/project-stats";
    }
}
