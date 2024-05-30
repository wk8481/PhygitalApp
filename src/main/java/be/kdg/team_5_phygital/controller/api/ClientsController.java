package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.ClientDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateClientDto;
import be.kdg.team_5_phygital.domain.Client;
import be.kdg.team_5_phygital.service.ClientService;
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
public class ClientsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    public ClientsController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<ClientDto> getSharingPlatformAdmin(@PathVariable("id") int sharingPlatformAdminId) {
        Client client = clientService.getSharingPlatformAdmin(sharingPlatformAdminId);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(client, ClientDto.class));
    }

    @GetMapping
    ResponseEntity<List<ClientDto>> getAllSharingPlatformAdmins() {
        List<Client> allClients = clientService.getAllSharingPlatformAdmins();
        if (allClients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ClientDto> clientDtos = allClients.stream().map(client -> modelMapper.map(client, ClientDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(clientDtos);
        }
    }

    @PatchMapping("{sharingPlatformAdminId}")
    ResponseEntity<Void> updateSharingPlatformAdmin(@PathVariable int sharingPlatformAdminId, @RequestBody UpdateClientDto updateClientDto) {
        if (clientService.updateSharingPlatformAdmin(sharingPlatformAdminId, updateClientDto.getName(), updateClientDto.getEmail())) {
            logger.info("Updating sharing platform admin to: {}", updateClientDto.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Could not find sharing platform admin");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
