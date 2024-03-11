package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @GetMapping("login")
    public String showLogin() {
        return "admin/login";
    }

    @GetMapping({"dashboard", ""})
    public String getDashboardPage() {
        return "admin/dashboard";
    }

    @GetMapping("sharing-platform")
    public String getSharingPlatformPage() {
        return "admin/sharing-platform";
    }

    @GetMapping("platform-stats")
    public String getPlatformStatsPage() {
        return "admin/platform-stats";
    }

    @GetMapping("project-stats")
    public String getProjectStatsPage() {
        return "admin/project-stats";
    }
}
