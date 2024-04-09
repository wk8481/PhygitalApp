package be.kdg.team_5_phygital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class loginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // This should return the name of your login HTML view
    }


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}