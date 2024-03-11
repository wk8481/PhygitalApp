package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sharing-platform")
public class SharingPlatformController {
    @GetMapping("login")
    public String showLogin() {
        return "sharing-platform/login";
    }

    @GetMapping("resetPassword")
    public String getResetPasswordPage() {
        return "sharing-platform/reset-password";
    }

    @GetMapping({"dashboard", ""})
    public String getDashboardPage() {
        return "sharing-platform/dashboard";
    }

    @GetMapping("project")
    public String getProjectPage() {
        return "sharing-platform/project";
    }

    @GetMapping("theme")
    public String getThemePage() {
        return "sharing-platform/theme";
    }

    @GetMapping("flow")
    public String getFlowPage() {
        return "sharing-platform/flow";
    }

    @GetMapping("sub-theme")
    public String getSubThemePage() {
        return "sharing-platform/sub-theme";
    }

    @GetMapping("question")
    public String getQuestionPage() {
        return "sharing-platform/question";
    }

    @GetMapping("supervisor")
    public String getSupervisorPage() {
        return "sharing-platform/supervisor";
    }
}
