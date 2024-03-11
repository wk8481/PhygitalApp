package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @GetMapping("login")
    public String getLogin() {
        return "admin/login";
    }

    @GetMapping("dashboard")
    public String getDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("sharing-platform")
    public String getSharingPlatform() {
        return "admin/sharing-platform";
    }
}
