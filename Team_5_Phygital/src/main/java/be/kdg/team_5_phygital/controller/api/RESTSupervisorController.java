package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSupervisorDto;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.service.SuperVisorService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sharing-platform/{platformId}/supervisor")
public class RESTSupervisorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SuperVisorService superVisorService;


    public RESTSupervisorController(SuperVisorService superVisorService) {
        this.superVisorService = superVisorService;
    }

    @Transactional
    @PatchMapping("/{superVisorId}/update")
    public Supervisor updateSuperVisorName(@PathVariable int superVisorId, @RequestBody UpdateSupervisorDto updatedSupervisor){
        Supervisor original = superVisorService.getSupervisorById(superVisorId).orElse(null);
        Supervisor updated = new Supervisor(superVisorId, updatedSupervisor.getName(), updatedSupervisor.getEmail(), original.getPassword(), original.getSharingPlatform(), original.getProjects());
        return superVisorService.updateSupervisor(updated);
    }

}
