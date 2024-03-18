package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("installation")
public class InstallationController {
    @GetMapping("login")
    public String showLogin() {
        return "installation/login";
    }

    @GetMapping({"dashboard", "", "/"})
    public String getDashboardPage() {
        return "installation/dashboard";
    }

    @GetMapping("flow-selection")
    public String getFlowSelectionPage() {
        return "installation/flow-selection";
    }

    @GetMapping("contact-details")
    public String getContactDetailsPage() {
        return "installation/contact-details";
    }
}
