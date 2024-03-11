package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sharing-platform")
public class SharingPlatformController {
    @GetMapping("login")
    public String getLogin() {
        return "sharing-platform/login";
    }

    @GetMapping("dashboard")
    public String getDashboard() {

        return "sharing-platform/dashboard";
    }

    @GetMapping("project")
    public String getProject() {
        return "sharing-platform/project";
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

    @GetMapping("supervisor")
    public String getSupervisor() {
        return "sharing-platform/supervisor";
    }
}
