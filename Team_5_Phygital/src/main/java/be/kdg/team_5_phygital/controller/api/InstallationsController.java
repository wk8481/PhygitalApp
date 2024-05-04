package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.InstallationDto;
import be.kdg.team_5_phygital.controller.api.dto.NewInstallationDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateInstallationDto;
import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.domain.Project;
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

    @Transactional
    public Installation saveInstallation(String name) {
        return installationRepository.save(new Installation(name));
    }

    public boolean updateInstallation(int installationId, String name) {
        Installation installation = installationRepository.findById(installationId).orElse(null);
        if (installation == null) {
            return false;
        }
        installation.setName(name);
        installationRepository.save(installation);
        return true;
    }

    @Transactional
    public boolean deleteInstallation(int installationId) {
        Optional<Installation> installation = installationRepository.findById(installationId);
        if (installation.isEmpty()) {
            return false;
        }
        installationRepository.deleteById(installationId);
        return true;
    }
}
