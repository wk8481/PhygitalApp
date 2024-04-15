package be.kdg.team_5_phygital.controller.mvc;

import be.kdg.team_5_phygital.controller.mvc.viewmodel.FlowViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.ProjectViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.SubThemeViewModel;
import be.kdg.team_5_phygital.controller.mvc.viewmodel.ThemeViewModel;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.service.FlowService;
import be.kdg.team_5_phygital.service.ProjectService;
import be.kdg.team_5_phygital.service.SubThemeService;
import be.kdg.team_5_phygital.service.ThemeService;
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
    private final SubThemeService subThemeService;

    public InstallationController(FlowService flowService, ProjectService projectService, ThemeService themeService, SubThemeService subThemeService) {
        this.flowService = flowService;
        this.projectService = projectService;
        this.themeService = themeService;
        this.subThemeService = subThemeService;
    }

    @GetMapping("project-selection")
    public ModelAndView getProjectSelectionPage() {
        var mav = new ModelAndView();
        mav.setViewName("installation/project-selection");
        mav.addObject("all_projects",
        projectService.getAllProjects().stream().map(project -> new ProjectViewModel(project.getId(), project.getName())).toList());
        return mav;

    }

    @GetMapping("flow-selection")
    public ModelAndView getFlowSelectionPage(@RequestParam("projectId") int projectId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/flow-selection");
        mav.addObject("all_flows",
        flowService.getFlowsByProjectId(projectId).stream()
                .map(flow -> new FlowViewModel(flow.getId(), flow.getName(), flow.isCircular())).toList());
        return mav;
    }


    @GetMapping("theme-description")
    public ModelAndView getThemeDescriptionPage(@RequestParam("projectId") int projectId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/theme-description");

        // Get the theme by projectId
        Theme theme = themeService.getThemeByProjectId(projectId);

        // Convert the theme to ThemeViewModel
        mav.addObject("one_theme", new ThemeViewModel(theme.getId(), theme.getName(), theme.getInformation()));

        return mav;
    }

    @GetMapping("subthemes-description")
    public ModelAndView getSubThemesDescriptionPage(@RequestParam("flowId") int flowId) {
        var mav = new ModelAndView();
        mav.setViewName("installation/theme-description");

        mav.addObject("all_subthemes",
           subThemeService.getSubThemeByFlowId(flowId).stream()
                    .map(subtheme -> new SubThemeViewModel(subtheme.getId(), subtheme.getName(), subtheme.getInformation(), subtheme.getFlow().getId())).toList());
        return mav;
    }


    @GetMapping("multiple-choice-question")
    public String getMultipleChoiceQuestionPage() {
        return "installation/multiple-choice-question";
    }

    @GetMapping("open-question")
    public String getOpenQuestionPage() {
        return "installation/open-question";
    }

    @GetMapping("closed-question")
    public String getClosedQuestionPage() {
        return "installation/closed-question";
    }

    @GetMapping("ranged-question")
    public String getRangedQuestionPage(){
        return "installation/ranged-question";
    }





    @GetMapping("contact-details")
    public String getContactDetailsPage() {
        return "installation/contact-details";
    }
}
