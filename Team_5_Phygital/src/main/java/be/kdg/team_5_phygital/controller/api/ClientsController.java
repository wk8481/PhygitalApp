package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformAdminDto;
import be.kdg.team_5_phygital.service.SharingPlatformAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
    private final SharingPlatformAdminService clientService;

    public ClientsController(SharingPlatformAdminService clientService) {
        this.clientService = clientService;
    }

    @PatchMapping("{clientId}")
    ResponseEntity<Void> updateClient(@PathVariable int clientId, @RequestBody UpdateSharingPlatformAdminDto updateClient) {
        if (clientService.updateSharingPlatformAdmin(clientId, updateClient.getName(), updateClient.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
