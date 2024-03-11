package be.kdg.team_5_phygital.controller;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.service.ProjectService;
import be.kdg.team_5_phygital.service.SharingPlatformService;
import be.kdg.team_5_phygital.service.SuperVisorService;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectService projectService;
    private final SharingPlatformService sharingPlatformService;
    private final SuperVisorService supervisorService;

    public SharingPlatformController(ProjectService projectService, SharingPlatformService sharingPlatformService, SuperVisorService supervisorService) {
        this.projectService = projectService;
        this.sharingPlatformService = sharingPlatformService;
        this.supervisorService = supervisorService;
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

        return "sharing-platform/project";
    }

    @GetMapping("theme")
    public String getTheme() {
        return "sharing-platform/theme";
    }

    @GetMapping("flow")
    public String getFlow() {
        return "sharing-platform/flow";
    }

    @GetMapping("sub-theme")
    public String getSubTheme() {
        return "sharing-platform/sub-theme";
    }

    @GetMapping("question")
    public String getQuestion() {
        return "sharing-platform/question";
    }

    @GetMapping("{platformId}/supervisor/{supervisorId}")
    public String getSupervisor(@PathVariable int platformId, @PathVariable int supervisorId, Model model) {
        model.addAttribute("sv", supervisorService.getSupervisorById(supervisorId).orElse(null));
        return "sharing-platform/supervisor";
    }
}
