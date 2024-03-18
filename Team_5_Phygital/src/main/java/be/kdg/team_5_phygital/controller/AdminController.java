package be.kdg.team_5_phygital.controller;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.service.ProjectService;
import be.kdg.team_5_phygital.service.SharingPlatformAdminService;
import be.kdg.team_5_phygital.service.SharingPlatformService;
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

    public AdminController(SharingPlatformService sharingPlatformService, SharingPlatformAdminService sharingPlatformAdminService, ProjectService projectService) {
        this.sharingPlatformService = sharingPlatformService;
        this.sharingPlatformAdminService = sharingPlatformAdminService;
        this.projectService = projectService;
    }

    @GetMapping("login")
    public String showLogin() {
        return "admin/login";
    }

    @GetMapping({"dashboard", "", "/"})
    public String getDashboardPage(Model model) {
        List<SharingPlatform> platformList = sharingPlatformService.getAllSharingPlatforms();
        model.addAttribute("platforms", platformList);
        return "admin/dashboard";
    }

    @GetMapping("sharing-platform/{platformId}")
    public String getSharingPlatformPage(@PathVariable int platformId, Model model) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatformById(platformId).orElse(null);
        SharingPlatformAdmin client = sharingPlatformAdminService.findAllSharingPlatformAdmins().get(0);
        model.addAttribute("platform", sharingPlatform);
        model.addAttribute("client", client);
        return "admin/sharing-platform";
    }

    @GetMapping("sharing-platform/{platformId}/client/{clientId}")
    public String getClientPage(@PathVariable int platformId, @PathVariable int clientId, Model model) {
        SharingPlatformAdmin client = sharingPlatformAdminService.findAllSharingPlatformAdmins().get(0);
        model.addAttribute("client", client);
        return "admin/client";
    }

    @GetMapping("sharing-platform/{platformId}/stats")
    public String getPlatformStatsPage(@PathVariable int platformId, Model model) {
        SharingPlatform sharingPlatform = sharingPlatformService.getSharingPlatformById(platformId).orElse(null);
        model.addAttribute("platform", sharingPlatform);
        return "admin/platform-stats";
    }

    @GetMapping("sharing-platform/{platformId}/{projectId}/stats")
    public String getProjectStatsPage(@PathVariable int platformId, @PathVariable int projectId) {
        return "admin/project-stats";
    }
}
