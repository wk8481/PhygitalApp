package be.kdg.team_5_phygital.controller.mvc;


import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.service.CommentService;
import be.kdg.team_5_phygital.service.ProjectService;
import be.kdg.team_5_phygital.service.ThemeService;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("web-app")
public class WebAppController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProjectService projectService;
    private final ThemeService themeService;
    private final CommentService commentService;

    public WebAppController(ProjectService projectService, ThemeService themeService, CommentService commentService) {
        this.projectService = projectService;
        this.themeService = themeService;
        this.commentService = commentService;
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

    @GetMapping("project/{projectId}")
    public String getProject(@PathVariable int projectId, Model model) {
        model.addAttribute("project", projectService.getProject(projectId));
        model.addAttribute("theme", themeService.getThemeByProjectId(projectId));
        model.addAttribute("comments", commentService.getCommentsByProjectId(projectId));
        return "web-app/project";
    }
}
