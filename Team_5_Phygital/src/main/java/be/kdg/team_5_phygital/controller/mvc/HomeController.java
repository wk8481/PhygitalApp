package be.kdg.team_5_phygital.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // This assumes index.html is a Thymeleaf template in src/main/resources/templates
        return "index";
    }
}
