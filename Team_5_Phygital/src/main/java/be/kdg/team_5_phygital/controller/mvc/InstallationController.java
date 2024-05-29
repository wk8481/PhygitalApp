package be.kdg.team_5_phygital.controller.mvc;

import be.kdg.team_5_phygital.controller.mvc.viewmodel.FlowViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.ProjectViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.SubThemeViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.ThemeViewModel;
import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    public InstallationController(FlowService flowService, ProjectService projectService, ThemeService themeService, QuestionService questionService, SubThemeService subThemeService, PossibleAnswerService possibleAnswerService) {
        this.flowService = flowService;
        this.projectService = projectService;
        this.themeService = themeService;
        this.questionService = questionService;
        this.subThemeService = subThemeService;
        this.possibleAnswerService = possibleAnswerService;
    }

    @GetMapping("project-selection")
    public ModelAndView getProjectSelectionPage() {
        var mav = new ModelAndView();
        mav.setViewName("installation/project-selection");
        mav.addObject("all_projects", projectService.getAllProjects().stream().map(project -> new ProjectViewModel(project.getId(), project.getName())).toList());
        return mav;
    }

    @GetMapping("flow-selection")
    public ModelAndView getFlowSelectionPage(@RequestParam("projectId") int projectId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/flow-selection");
        mav.addObject("project", projectService.getProject(projectId));
        mav.addObject("all_flows", flowService.getFlowsByProjectId(projectId).stream().map(flow -> new FlowViewModel(flow.getId(), flow.getName(), flow.isCircular())).toList());
        return mav;
    }


    @GetMapping("theme-description")
    public ModelAndView getThemeDescriptionPage(@RequestParam("flowId") int flowId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/theme-description");
        Flow flow = flowService.getFlow(flowId);
        Theme theme = themeService.getThemeByProjectId(flow.getProject().getId());
        mav.addObject("one_flow", new FlowViewModel(flow.getId(), flow.getName(), flow.isCircular()));
        mav.addObject("one_theme", new ThemeViewModel(theme.getId(), theme.getName(), theme.getInformation()));
        mav.addObject("flow", flow);
        return mav;
    }

    @GetMapping("sub-themes")
    public ModelAndView getSubThemesPage(@RequestParam("flowId") int flowId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/sub-themes");
        Flow flow = flowService.getFlow(flowId);
        Theme theme = themeService.getThemeByProjectId(flow.getProject().getId());
        mav.addObject("all_sub_themes", subThemeService.getSubThemeByFlowId(flowId).stream().map(subtheme -> new SubThemeViewModel(subtheme.getId(), subtheme.getName(), subtheme.getInformation(), subtheme.getFlow().getId())).toList());
        return mav;
    }

    @GetMapping("questions")
    public ModelAndView getQuestions(@RequestParam("subThemeId") int subThemeId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/question");
        SubTheme subTheme = subThemeService.getSubTheme(subThemeId);
        List<Question> questions = questionService.getQuestionsBySubTheme(subTheme);
        List<PossibleAnswers> possibleAnswers = possibleAnswerService.getPossibleAnswersByQuestionId(questions);
        boolean isCircular = subTheme.isCircularFlow();
        mav.addObject("questions", questions);
        mav.addObject("possibleAnswers", possibleAnswers);
        mav.addObject("isCircular", isCircular);
        return mav;
    }

    @GetMapping("flowCompleted")
    public ModelAndView flowComplete(){
        var mav = new ModelAndView();
        mav.setViewName("installation/flow-completed");
        return mav;
    }

    @GetMapping("contact-details")
    public String getContactDetailsPage() {
        return "installation/contact-details";
    }
}
