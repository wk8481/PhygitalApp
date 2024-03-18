package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.UpdateProjectDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformDto;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.service.SharingPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class RESTAdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SharingPlatformService sharingPlatformService;


    public RESTAdminController(SharingPlatformService sharingPlatformService) {
        this.sharingPlatformService = sharingPlatformService;
    }

    @PatchMapping("{platformId}/update")
    public String updateSharingPlatform(@PathVariable int platformId, @RequestBody UpdateSharingPlatformDto updatedSharingPlatform) {
        sharingPlatformService.updateSharingPlatform(new SharingPlatform(updatedSharingPlatform.getId(), updatedSharingPlatform.getName()));
        return null;
        }
    }
