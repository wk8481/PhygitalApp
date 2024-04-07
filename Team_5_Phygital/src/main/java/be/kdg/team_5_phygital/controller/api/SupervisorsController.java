package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewSupervisorDto;
import be.kdg.team_5_phygital.controller.api.dto.SupervisorDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSupervisorDto;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.repository.SupervisorRepository;
import be.kdg.team_5_phygital.service.SupervisorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supervisors")
public class SupervisorsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SupervisorService supervisorService;
    private final SupervisorRepository supervisorRepository;
    private final ModelMapper modelMapper;

    public SupervisorsController(SupervisorService supervisorService, SupervisorRepository supervisorRepository, ModelMapper modelMapper) {
        this.supervisorService = supervisorService;
        this.supervisorRepository = supervisorRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<SupervisorDto> saveSupervisor(@RequestBody @Valid NewSupervisorDto supervisorDto) {
        if (supervisorRepository.findByName(supervisorDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("creating new supervisor: " + supervisorDto);
        Supervisor createdSupervisor = supervisorService.saveSupervisor(supervisorDto.getName(), supervisorDto.getEmail(), supervisorDto.getSharingPlatformId());
        return new ResponseEntity<>(modelMapper.map(createdSupervisor, SupervisorDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{supervisorId}")
    ResponseEntity<Void> updateSupervisor(@PathVariable int supervisorId, @RequestBody UpdateSupervisorDto updateSupervisorDto) {
        if (supervisorService.updateSupervisor(supervisorId, updateSupervisorDto.getName(), updateSupervisorDto.getEmail())) {
            logger.info("updating supervisor to: "+ updateSupervisorDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("error updating supervisor");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{supervisorId}")
    ResponseEntity<Void> deleteSupervisor(@PathVariable("supervisorId") int supervisorId) {
        if (supervisorService.deleteSupervisor(supervisorId)) {
            logger.info("deleting supervisor");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
