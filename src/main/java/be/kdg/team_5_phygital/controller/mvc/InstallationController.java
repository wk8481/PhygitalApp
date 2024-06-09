package be.kdg.team_5_phygital.controller.mvc;

import be.kdg.team_5_phygital.controller.mvc.viewmodel.FlowViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.ProjectViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.SubThemeViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.ThemeViewModel;
import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/installation")
public class InstallationController {

    private final FlowService flowService;
    private final ProjectService projectService;
    private final ThemeService themeService;
    private final QuestionService questionService;
    private final SubThemeService subThemeService;
    private final PossibleAnswerService possibleAnswerService;
    private final SessionService sessionService;
    private final UserEmailService userEmailService;
    private final SharingPlatformService sharingPlatformService;

    public InstallationController(FlowService flowService, ProjectService projectService, ThemeService themeService, QuestionService questionService, SubThemeService subThemeService, PossibleAnswerService possibleAnswerService, SessionService sessionService, UserEmailService userEmailService, SharingPlatformService sharingPlatformService) {
        this.flowService = flowService;
        this.projectService = projectService;
        this.themeService = themeService;
        this.questionService = questionService;
        this.subThemeService = subThemeService;
        this.possibleAnswerService = possibleAnswerService;
        this.sessionService = sessionService;
        this.userEmailService = userEmailService;
        this.sharingPlatformService = sharingPlatformService;
    }

    @GetMapping("project-selection")
    public ModelAndView getProjectSelectionPage() {
        var mav = new ModelAndView();
        mav.setViewName("installation/project-selection");
        mav.addObject("all_projects", projectService.getAllProjects().stream().map(project -> new ProjectViewModel(project.getId(), project.getName(), project.getLogoUrl())).toList());
        return mav;
    }

    @GetMapping("flow-selection")
    public ModelAndView getFlowSelectionPage(@RequestParam("projectId") int projectId) {
        var mav = new ModelAndView();
        Project project = projectService.getProject(projectId);
        SharingPlatform platform = project.getSharingPlatform();
        mav.setViewName("installation/flow-selection");
        mav.addObject("project", projectService.getProject(projectId));
        mav.addObject("all_flows", flowService.getFlowsByProjectId(projectId).stream().map(flow -> new FlowViewModel(flow.getId(), flow.getName(), flow.isCircular())).toList());
        mav.addObject("project", project);
        mav.addObject("platform", platform);
        return mav;
    }

    @GetMapping("manual")
    public ModelAndView getManualPage() {
        var mav = new ModelAndView();
        mav.setViewName("installation/manual");
        return mav;
    }


    @GetMapping("theme-description")
    public ModelAndView getThemeDescriptionPage(@RequestParam("flowId") int flowId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/theme-description");
        Flow flow = flowService.getFlow(flowId);
        Theme theme = themeService.getThemeByProjectId(flow.getProject().getId());
        Project project = projectService.getProject(flow.getProject().getId());
        SharingPlatform platform = project.getSharingPlatform();
        mav.addObject("one_flow", new FlowViewModel(flow.getId(), flow.getName(), flow.isCircular()));
        mav.addObject("one_theme", new ThemeViewModel(theme.getId(), theme.getName(), theme.getInformation(), theme.getMediaUrl()));
        mav.addObject("flow", flow);
        mav.addObject("project", project);
        mav.addObject("platform", platform);
        return mav;
    }

    @GetMapping("sub-themes")
    public ModelAndView getSubThemesPage(@RequestParam("flowId") int flowId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/sub-themes");
        Flow flow = flowService.getFlow(flowId);
        Theme theme = themeService.getThemeByProjectId(flow.getProject().getId());
        Project project = projectService.getProject(flow.getProject().getId());
        SharingPlatform platform = project.getSharingPlatform();
        mav.addObject("all_sub_themes", subThemeService.getSubThemeByFlowIdAndIsVisible(flowId).stream().map(subTheme -> new SubThemeViewModel(subTheme.getId(), subTheme.getName(), subTheme.getInformation(), subTheme.getMediaUrl(), subTheme.getFlow().getId())).toList());
        mav.addObject("project", project);
        mav.addObject("platform", platform);
        mav.addObject("theme", theme);
        return mav;
    }

    @GetMapping("questions")
    public ModelAndView getQuestions(@RequestParam("subThemeId") int subThemeId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/question");
        SubTheme subTheme = subThemeService.getSubTheme(subThemeId);
        List<Question> questions = questionService.getQuestionsBySubThemeAndVisible(subTheme);
        List<PossibleAnswers> possibleAnswers = possibleAnswerService.getPossibleAnswersByQuestionId(questions);
        boolean isCircular = subTheme.isCircularFlow();
        Project project = projectService.getProject(subTheme.getFlow().getProject().getId());
        SharingPlatform platform = project.getSharingPlatform();
        Theme theme = themeService.getThemeByProjectId(project.getId());
        mav.addObject("questions", questions);
        mav.addObject("possibleAnswers", possibleAnswers);
        mav.addObject("isCircular", isCircular);
        mav.addObject("project", project);
        mav.addObject("platform", platform);
        mav.addObject("subTheme", subTheme);
        mav.addObject("theme", theme);
        return mav;
    }

    @GetMapping("contact-details")
    public ModelAndView getContactDetailsPage(@RequestParam("platformId") int platformId, @RequestParam("projectId") int projectId) {
        var mav = new ModelAndView();
        SharingPlatform platform = sharingPlatformService.getSharingPlatform(platformId);
        Project project = projectService.getProject(projectId);
        mav.setViewName("installation/contact-details");
        mav.addObject("platform", platform);
        mav.addObject("project", project);
        return mav;
    }

    @PostMapping("contact-details")
    public String addContactEmail(@RequestParam("email") String email, @RequestParam("platformId") int platformId, @RequestParam("projectId") int projectId) {
        Session session = sessionService.getLatestSession();
        UserEmail userEmail = userEmailService.saveUserEmail(email, session);
        sessionService.addUserEmail(session, userEmail);

        return "redirect:/installation/contact-details?platformId=" + platformId + "&projectId=" + projectId;
    }

    @GetMapping("project-information/{projectId}")
    public ModelAndView getProjectInformationPage(@PathVariable("projectId") int projectId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/project-information");
        Project project = projectService.getProject(projectId);
        Theme theme = themeService.getThemeByProjectId(projectId);
        mav.addObject("project", project);
        mav.addObject("theme", theme);
        return mav;
    }

    @GetMapping("organization-information/{platformId}")
    public ModelAndView getOrganizationInformationPage(@PathVariable("platformId") int platformId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/organization-information");
        SharingPlatform platform = sharingPlatformService.getSharingPlatform(platformId);
        mav.addObject("platform", platform);
        return mav;
    }

    @GetMapping("privacy-statement")
    public ModelAndView getPrivacyStatementPage() {
        var mav = new ModelAndView();
        mav.setViewName("installation/privacy-statement");
        return mav;
    }
}
