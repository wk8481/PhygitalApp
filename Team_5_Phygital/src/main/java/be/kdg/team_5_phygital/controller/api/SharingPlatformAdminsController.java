package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.SharingPlatformAdminDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformAdminDto;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.service.SharingPlatformAdminService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class SharingPlatformAdminsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SharingPlatformAdminService sharingPlatformAdminService;
    private final ModelMapper modelMapper;

    public SharingPlatformAdminsController(SharingPlatformAdminService sharingPlatformAdminService, ModelMapper modelMapper) {
        this.sharingPlatformAdminService = sharingPlatformAdminService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<SharingPlatformAdminDto> getSharingPlatformAdmin(@PathVariable("id") int sharingPlatformAdminId) {
        SharingPlatformAdmin sharingPlatformAdmin = sharingPlatformAdminService.getSharingPlatformAdmin(sharingPlatformAdminId);
        if (sharingPlatformAdmin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(sharingPlatformAdmin, SharingPlatformAdminDto.class));
    }

    @GetMapping
    ResponseEntity<List<SharingPlatformAdminDto>> getAllSharingPlatformAdmins() {
        List<SharingPlatformAdmin> allClients = sharingPlatformAdminService.getAllSharingPlatformAdmins();
        if (allClients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<SharingPlatformAdminDto> clientDtos = allClients.stream().map(client -> modelMapper.map(client, SharingPlatformAdminDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(clientDtos);
        }
    }

    @PatchMapping("{sharingPlatformAdminId}")
    ResponseEntity<Void> updateSharingPlatformAdmin(@PathVariable int sharingPlatformAdminId, @RequestBody UpdateSharingPlatformAdminDto updateSharingPlatformAdminDto) {
        if (sharingPlatformAdminService.updateSharingPlatformAdmin(sharingPlatformAdminId, updateSharingPlatformAdminDto.getName(), updateSharingPlatformAdminDto.getEmail())) {
            logger.info("Updating sharing platform admin to: {}", updateSharingPlatformAdminDto.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Could not find sharing platform admin");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
