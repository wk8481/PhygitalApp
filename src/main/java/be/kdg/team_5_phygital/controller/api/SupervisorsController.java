package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewSupervisorDto;
import be.kdg.team_5_phygital.controller.api.dto.SupervisorDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSupervisorDto;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.service.SupervisorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/supervisors")
public class SupervisorsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SupervisorService supervisorService;
    private final ModelMapper modelMapper;

    public SupervisorsController(SupervisorService supervisorService, ModelMapper modelMapper) {
        this.supervisorService = supervisorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<SupervisorDto> getSupervisor(@PathVariable("id") int supervisorId) {
        Supervisor supervisor = supervisorService.getSupervisor(supervisorId);
        if (supervisor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(supervisor, SupervisorDto.class));
    }

    @GetMapping
    ResponseEntity<List<SupervisorDto>> getAllSupervisors() {
        List<Supervisor> allSupervisors = supervisorService.getAllSupervisors();
        if (allSupervisors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<SupervisorDto> supervisorDtos = allSupervisors.stream().map(supervisor -> modelMapper.map(supervisor, SupervisorDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(supervisorDtos);
        }
    }

    @PostMapping
    ResponseEntity<SupervisorDto> saveSupervisor(@RequestBody @Valid NewSupervisorDto supervisorDto) {
        if (supervisorService.getSupervisorByName(supervisorDto.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Creating new supervisor: {}", supervisorDto.getName());
        Supervisor createdSupervisor = supervisorService.saveSupervisor(supervisorDto.getName(), supervisorDto.getEmail(), supervisorDto.getSharingPlatformId());
        return new ResponseEntity<>(modelMapper.map(createdSupervisor, SupervisorDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{supervisorId}")
    ResponseEntity<Void> updateSupervisor(@PathVariable int supervisorId, @RequestBody UpdateSupervisorDto updateSupervisorDto) {
        if (supervisorService.updateSupervisor(supervisorId, updateSupervisorDto.getName(), updateSupervisorDto.getEmail())) {
            logger.info("Updating supervisor to: {}", updateSupervisorDto.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Error updating supervisor");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{supervisorId}")
    ResponseEntity<Void> deleteSupervisor(@PathVariable("supervisorId") int supervisorId) {
        if (supervisorService.deleteSupervisor(supervisorId)) {
            logger.info("Deleting supervisor");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
