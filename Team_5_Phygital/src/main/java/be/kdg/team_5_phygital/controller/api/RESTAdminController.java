package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformAdminDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformDto;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.service.SharingPlatformAdminService;
import be.kdg.team_5_phygital.service.SharingPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class RESTAdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SharingPlatformService sharingPlatformService;
    private final SharingPlatformAdminService sharingPlatformAdminService;

    public RESTAdminController(SharingPlatformService sharingPlatformService, SharingPlatformAdminService sharingPlatformAdminService) {
        this.sharingPlatformService = sharingPlatformService;
        this.sharingPlatformAdminService = sharingPlatformAdminService;
    }

    @PatchMapping("{platformId}/update")
    ResponseEntity<Void> updateSharingPlatform(@PathVariable int platformId, @RequestBody UpdateSharingPlatformDto updateSharingPlatform) {
        if (sharingPlatformService.updateSharingPlatform(platformId, updateSharingPlatform.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{clientId}/update")
    ResponseEntity<Void> updateClient(@PathVariable int clientId, @RequestBody UpdateSharingPlatformAdminDto updateClient) {
        if (sharingPlatformAdminService.updateSharingPlatformAdmin(clientId, updateClient.getName(), updateClient.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
