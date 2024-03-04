package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("entrance")
    public String getEntrance() {
        return "entrance";
    }

    @GetMapping("installation-login")
    public String getInstallationLogin() {
        return "installation-login";
    }

    @GetMapping("supervisor-login")
    public String getSupervisorLogin() {
        return "supervisor-login";
    }

    @GetMapping("sharing-platform-login")
    public String getSharingPlatformLogin() {
        return "sharing-platform-login";
    }

    @GetMapping("admin-login")
    public String getAdminLogin() {
        return "admin-login";
    }
}
