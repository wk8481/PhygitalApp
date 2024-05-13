package be.kdg.team_5_phygital.controller.mvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("web-app")
public class WebAppController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public WebAppController() {
    }

    @GetMapping("home")
    public String getHome() {
        return "web-app/home";
    }

    @GetMapping("info")
    public String getInfo() {
        return "web-app/info";
    }
}
