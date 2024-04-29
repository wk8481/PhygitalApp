package be.kdg.team_5_phygital.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller("/security")
public class SecurityController {

    @GetMapping("/login")
    public String login() {
        return "security/login"; // This should return the name of your login HTML view
    }


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "security/access-denied";
    }
}
