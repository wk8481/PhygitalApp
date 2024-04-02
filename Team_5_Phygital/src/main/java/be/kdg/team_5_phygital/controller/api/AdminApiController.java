package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.*;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.repository.SharingPlatformAdminRepository;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import be.kdg.team_5_phygital.service.SharingPlatformAdminService;
import be.kdg.team_5_phygital.service.SharingPlatformService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SharingPlatformService sharingPlatformService;
    private final SharingPlatformAdminService clientService;
    private final SharingPlatformRepository sharingPlatformRepository;
    private final SharingPlatformAdminRepository clientRepository;
    private final ModelMapper modelMapper;

    public AdminApiController(SharingPlatformService sharingPlatformService, SharingPlatformAdminService clientService, SharingPlatformRepository sharingPlatformRepository, SharingPlatformAdminRepository clientRepository, ModelMapper modelMapper) {
        this.sharingPlatformService = sharingPlatformService;
        this.clientService = clientService;
        this.sharingPlatformRepository = sharingPlatformRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("platform/{platformId}")
    ResponseEntity<SharingPlatformDto> saveSharingPlatform(@PathVariable int platformId, @RequestBody @Valid NewSharingPlatformDto sharingPlatformDto) {
        if (sharingPlatformRepository.findByName(sharingPlatformDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        SharingPlatform createdSharingPlatform = sharingPlatformService.saveSharingPlatform(sharingPlatformDto.getName(), sharingPlatformDto.getContactEmail());
        return new ResponseEntity<>(modelMapper.map(createdSharingPlatform, SharingPlatformDto.class), HttpStatus.CREATED);
    }

    @PostMapping("platform/{platformId}/client/{clientId}")
    ResponseEntity<SharingPlatformAdminDto> saveSharingPlatformAdmin(@PathVariable int clientId, @RequestBody @Valid NewSharingPlatformAdminDto clientDto) {
        if (clientRepository.findByName(clientDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        SharingPlatformAdmin createdSharingPlatformAdmin = clientService.saveSharingPlatformAdmin(clientDto.getName(), clientDto.getEmail(),clientDto.getPassword());
        return new ResponseEntity<>(modelMapper.map(createdSharingPlatformAdmin, SharingPlatformAdminDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("platform/{platformId}/update")
    ResponseEntity<Void> updateSharingPlatform(@PathVariable int platformId, @RequestBody UpdateSharingPlatformDto updateSharingPlatform) {
        if (sharingPlatformService.updateSharingPlatform(platformId, updateSharingPlatform.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("platform/{platformId}/client/{clientId}/update")
    ResponseEntity<Void> updateClient(@PathVariable int platformId, @PathVariable int clientId, @RequestBody UpdateSharingPlatformAdminDto updateClient) {
        if (clientService.updateSharingPlatformAdmin(clientId, updateClient.getName(), updateClient.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
