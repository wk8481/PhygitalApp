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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SharingPlatformService sharingPlatformService;
    private final ClientService clientService;
    private final ProjectService projectService;
    private final SupervisorService supervisorService;
    private final ThemeService themeService;
    private final InstallationService installationService;
    private final FlowService flowService;
    private final SubThemeService subThemeService;
    private final QuestionService questionService;
    private final PossibleAnswerService possibleAnswerService;
    private final SessionService sessionService;

    public AdminController(SharingPlatformService sharingPlatformService, ClientService clientService, ProjectService projectService, SupervisorService supervisorService, ThemeService themeService, InstallationService installationService, FlowService flowService, SubThemeService subThemeService, QuestionService questionService, PossibleAnswerService possibleAnswerService, SessionService sessionService) {
        this.sharingPlatformService = sharingPlatformService;
        this.clientService = clientService;
        this.projectService = projectService;
        this.supervisorService = supervisorService;
        this.themeService = themeService;
        this.installationService = installationService;
        this.flowService = flowService;
        this.subThemeService = subThemeService;
        this.questionService = questionService;
        this.possibleAnswerService = possibleAnswerService;
        this.sessionService = sessionService;
    }

    @GetMapping("platform")
    public String getPlatform(Model model) {
        List<SharingPlatform> platforms = sharingPlatformService.getAllSharingPlatforms();
        List<Installation> installations = installationService.getAllInstallations();
        model.addAttribute("platforms", platforms);
        model.addAttribute("installations", installations);
        return "admin/platform";
    }


    @GetMapping("sharing-platform/{platformId}")
    public String getSharingPlatform(@PathVariable int platformId, Model model) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatform(platformId);
        Client client = clientService.getAllSharingPlatformAdmins().get(0);
        List<Project> projects = projectService.getProjectBySharingPlatformId(platformId);
        List<Supervisor> supervisors = supervisorService.findSupervisorBySharingPlatform(sharingPlatform);
        model.addAttribute("platform", sharingPlatform);
        model.addAttribute("client", client);
        model.addAttribute("projects", projects);
        model.addAttribute("supervisor", supervisors);
        return "admin/sharing-platform";
    }

    @GetMapping("installation/{installationId}")
    public String getInstallation(@PathVariable int installationId, Model model) {
        Installation installation = installationService.getInstallation(installationId);
        model.addAttribute("installation", installation);
        return "admin/installation";
    }

    @GetMapping("installation/new")
    public String getNewInstallation() {
        return "admin/new-installation";
    }


    @GetMapping("sharing-platform/new")
    public String getNewSharingPlatform() {
        return "admin/new-sharing-platform";
    }

    @GetMapping("sharing-platform/{platformId}/client/{clientId}")
    public String getClient(@PathVariable int platformId, @PathVariable int clientId, Model model) {
        Client client = clientService.getAllSharingPlatformAdmins().get(0);
        model.addAttribute("client", client);
        return "admin/client";
    }

    @GetMapping("sharing-platform/{platformId}/stats")
    public String getPlatformStats(@PathVariable int platformId, Model model) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatform(platformId);
        Project project = projectService.getAllProjects().stream().findFirst().orElse(null);
        model.addAttribute("platform", sharingPlatform);
        model.addAttribute("project", project);
        return "admin/sharing-platform-stats";
    }

    @GetMapping("{platformId}/project/{projectId}")
    public String getProject(@PathVariable int platformId, @PathVariable int projectId, Model model) {
        Project project = projectService.getProject(projectId);
        List<Flow> flows = flowService.getFlowsByProjectId(projectId);
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
        Project project = projectService.getProject(projectId);
        Theme theme = themeService.getThemeByProjectId(projectId);
        model.addAttribute("project", project);
        model.addAttribute("theme", theme);
        return "admin/theme";
    }

    @GetMapping("sharing-platform/{platformId}/{projectId}/stats")
    public String getProjectStats(@PathVariable int platformId, @PathVariable int projectId, Model model) {
        Project project = projectService.getProject(projectId);
        model.addAttribute("project", project);
        return "admin/project-stats";
    }

    @GetMapping("project/{projectId}/flow/{flowId}")
    public String getFlow(@PathVariable int projectId, @PathVariable int flowId, Model model) {
        Project project = projectService.getProject(projectId);
        Flow flow = flowService.getFlow(flowId);
        List<SubTheme> subThemes = subThemeService.getSubThemeByFlowId(flow.getId());
        model.addAttribute("project", project);
        model.addAttribute("flow", flow);
        model.addAttribute("st", subThemes);
        return "admin/flow";
    }

    @GetMapping("project/{projectId}/flow/new")
    public String getNewFlow(@PathVariable int projectId, Model model) {
        Project project = projectService.getProject(projectId);
        List<Installation> installations = installationService.getAllInstallations();
        model.addAttribute("project", project);
        model.addAttribute("installations", installations);
        return "admin/new-flow";
    }

    @GetMapping("flow/{flowId}/sub-theme/{subThemeId}")
    public String getSubTheme(@PathVariable int flowId, @PathVariable int subThemeId, Model model) {
        SubTheme subTheme = subThemeService.getSubTheme(subThemeId);
        List<Question> questions = questionService.getQuestionsBySubTheme(subTheme);
        Project project = flowService.getFlow(flowId).getProject();
        model.addAttribute("subTheme", subTheme);
        model.addAttribute("questions", questions);
        model.addAttribute("project", project);
        return "admin/sub-theme";
    }

    @GetMapping("flow/{flowId}/sub-theme/new")
    public String getNewSubTheme(@PathVariable int flowId, Model model) {
        Project project = flowService.getFlow(flowId).getProject();
        model.addAttribute("project", project);
        return "admin/new-sub-theme";
    }

    @GetMapping("sub-theme/{subThemeId}/question/{questionId}")
    public String getQuestion(@PathVariable int subThemeId, @PathVariable int questionId, Model model) {
        Question question = questionService.getQuestion(questionId);
        Project project = question.getSubTheme().getFlow().getProject();
        model.addAttribute("q", question);
        List<Question> questions = new ArrayList<>();
        List<PossibleAnswers> possibleAnswers = new ArrayList<>(4);
        questions.add(question);
        possibleAnswers = possibleAnswerService.getPossibleAnswersByQuestionId(questions);
        model.addAttribute("pA", possibleAnswers);
        return "admin/question";
    }

    @GetMapping("sub-theme/{subThemeId}/question/new")
    public String getNewQuestion(@PathVariable int subThemeId, Model model) {
        Project project = subThemeService.getSubTheme(subThemeId).getFlow().getProject();
        model.addAttribute("project", project);
        return "admin/new-question";
    }

    @GetMapping("{platformId}/supervisor/{supervisorId}")
    public String getSupervisor(@PathVariable int platformId, @PathVariable int supervisorId, Model model) {
        model.addAttribute("sv", supervisorService.getSupervisor(supervisorId));
        return "admin/supervisor";
    }

    @GetMapping("{platformId}/supervisor/new")
    public String getNewSupervisor(@PathVariable int platformId) {
        return "admin/new-supervisor";
    }

    @GetMapping("project/{projectId}/stats")
    public String getProjectStats(@PathVariable int projectId, Model model) {
        Project project = projectService.getProject(projectId);
        model.addAttribute("project", project);
        return "admin/project-stats";
    }

    @GetMapping("sub-theme/{subThemeId}/sessions")
    public String getSubThemeAnswers(@PathVariable int subThemeId, Model model) {
        Project project = projectService.getProject(subThemeService.getSubTheme(subThemeId).getFlow().getId());
        List<Session> session = sessionService.getSessions(subThemeService.getSubTheme(subThemeId));
        List<Session> session_questions = sessionService.getQuestionsOfSessions(session);
        List<Session> session_answers = sessionService.getAnswersOfSessions(session);
        model.addAttribute("project", project);
        model.addAttribute("sessions_q", session_questions);
        model.addAttribute("sessions_a", session_answers);
        return "admin/sub-theme-sessions";
    }
}
