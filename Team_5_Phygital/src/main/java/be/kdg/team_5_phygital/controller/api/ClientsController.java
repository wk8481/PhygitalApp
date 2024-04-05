package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewSharingPlatformAdminDto;
import be.kdg.team_5_phygital.controller.api.dto.SharingPlatformAdminDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSharingPlatformAdminDto;
import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import be.kdg.team_5_phygital.repository.SharingPlatformAdminRepository;
import be.kdg.team_5_phygital.service.SharingPlatformAdminService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
    private final SharingPlatformAdminService clientService;
    private final SharingPlatformAdminRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientsController(SharingPlatformAdminService clientService, SharingPlatformAdminRepository clientRepository, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("{platformId}")
    ResponseEntity<SharingPlatformAdminDto> saveClient(@PathVariable int platformId, @RequestBody @Valid NewSharingPlatformAdminDto clientDto) {
        if (clientRepository.findByName(clientDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        SharingPlatformAdmin createdSharingPlatformAdmin = clientService.saveSharingPlatformAdmin(clientDto.getName(), clientDto.getEmail(), clientDto.getPassword());
        return new ResponseEntity<>(modelMapper.map(createdSharingPlatformAdmin, SharingPlatformAdminDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{clientId}")
    ResponseEntity<Void> updateClient(@PathVariable int clientId, @RequestBody UpdateSharingPlatformAdminDto updateClient) {
        if (clientService.updateSharingPlatformAdmin(clientId, updateClient.getName(), updateClient.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{clientId}")
    ResponseEntity<Void> deleteClient(@PathVariable("clientId") int clientId) {
        if (clientService.deleteSharingPlatformAdmin(clientId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
