package be.kdg.team_5_phygital.controller.mvc;


import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.service.ProjectService;
import be.kdg.team_5_phygital.service.ThemeService;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("web-app")
public class WebAppController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProjectService projectService;
    private final ThemeService themeService;

    public WebAppController(ProjectService projectService, ThemeService themeService) {
        this.projectService = projectService;
        this.themeService = themeService;
    }

    @GetMapping("home")
    public String getHome(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "web-app/home";
    }

    @GetMapping("info")
    public String getInfo() {
        return "web-app/info";
    }

    @GetMapping("project")
    public String getProject(@RequestParam("projectId") int projectId, Model model) {
        model.addAttribute("project", projectService.getProject(projectId));
        model.addAttribute("theme", themeService.getThemeByProjectId(projectId));
        return "web-app/project";
    }
}
