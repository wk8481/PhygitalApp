package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("installation")
public class InstallationController {
    @GetMapping("login")
    public String getLogin() {
        return "installation/login";
    }

    @GetMapping("dashboard")
    public String getDashboard() {
        return "installation/dashboard";
    }

    @GetMapping("flow-selection")
    public String getFlowSelection() {
        return "installation/flow-selection";
    }
}
