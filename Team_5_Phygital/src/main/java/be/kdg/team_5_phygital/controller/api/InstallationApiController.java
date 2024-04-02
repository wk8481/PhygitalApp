package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.service.InstallationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/installation")
public class InstallationApiController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InstallationService installationService;

    public InstallationApiController(InstallationService installationService) {
        this.installationService = installationService;
    }



}
