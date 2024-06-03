package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.*;
import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.util.Location;
import be.kdg.team_5_phygital.repository.InstallationRepository;
import be.kdg.team_5_phygital.service.InstallationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/installations")
public class InstallationsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InstallationService installationService;
    private final InstallationRepository installationRepository;
    private final ModelMapper modelMapper;

    public InstallationsController(InstallationService installationService, InstallationRepository installationRepository, ModelMapper modelMapper) {
        this.installationService = installationService;
        this.installationRepository = installationRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<InstallationDto> getInstallation(@PathVariable("id") int installationId) {
        Installation installation = installationService.getInstallation(installationId);
        if (installation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(installation, InstallationDto.class));
    }

    @GetMapping
    ResponseEntity<List<InstallationDto>> getAllInstallations() {
        List<Installation> allInstallations = installationService.getAllInstallations();
        if (allInstallations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<InstallationDto> installationDtos = allInstallations.stream().map(installation -> modelMapper.map(installation, InstallationDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(installationDtos);
        }
    }

    @PostMapping
    ResponseEntity<InstallationDto> saveInstallation(@RequestBody @Valid NewInstallationDto installationDto) {
        if (installationRepository.findByName(installationDto.getName()).isPresent()) {
            logger.error("Installation already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
        logger.info("Installation created");
        Installation createdInstallation = installationService.saveInstallation(installationDto.getName(), installationDto.getProvince(), installationDto.getCity(), installationDto.getStreet(), installationDto.getStreetNumber());
        return new ResponseEntity<>(modelMapper.map(createdInstallation, InstallationDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{installationId}")
    ResponseEntity<Void> updateInstallation(@PathVariable int installationId,
                                            @RequestPart("updateInstallation") UpdateInstallationDto updateInstallationDto

                                            ) {
        if (installationService.updateInstallation(installationId, updateInstallationDto.getName(), updateInstallationDto.getProvince(), updateInstallationDto.getCity(), updateInstallationDto.getStreet(), updateInstallationDto.getStreetNumber())) {
            logger.info("Installation {} updated", updateInstallationDto);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Installation update failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{installationId}")
    ResponseEntity<Void> deleteInstallation(@PathVariable("installationId") int installationId) {
        if (installationService.deleteInstallation(installationId)) {
            logger.info("Installation deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.error("Installation could not be found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
