package be.kdg.team_5_phygital.controller.mvc;


import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.service.ProjectService;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("web-app")
public class WebAppController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProjectService projectService;

    public WebAppController(ProjectService projectService) {
        this.projectService = projectService;
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
}
