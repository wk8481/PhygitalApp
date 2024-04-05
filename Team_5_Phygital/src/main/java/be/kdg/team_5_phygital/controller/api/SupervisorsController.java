package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.NewSupervisorDto;
import be.kdg.team_5_phygital.controller.api.dto.SupervisorDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateSupervisorDto;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.repository.SupervisorRepository;
import be.kdg.team_5_phygital.service.SupervisorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supervisors")
public class SupervisorsController {
    private final SupervisorService supervisorService;
    private final SupervisorRepository supervisorRepository;
    private final ModelMapper modelMapper;

    public SupervisorsController(SupervisorService supervisorService, SupervisorRepository supervisorRepository, ModelMapper modelMapper) {
        this.supervisorService = supervisorService;
        this.supervisorRepository = supervisorRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("{platformId}")
    ResponseEntity<SupervisorDto> saveSupervisor(@PathVariable int platformId, @RequestBody @Valid NewSupervisorDto supervisorDto) {
        if (supervisorRepository.findByName(supervisorDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Supervisor createdSupervisor = supervisorService.saveSupervisor(supervisorDto.getName(), supervisorDto.getEmail());
        return new ResponseEntity<>(modelMapper.map(createdSupervisor, SupervisorDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{supervisorId}")
    ResponseEntity<Void> updateSupervisor(@PathVariable int supervisorId, @RequestBody UpdateSupervisorDto updateSupervisorDto) {
        if (supervisorService.updateSupervisor(supervisorId, updateSupervisorDto.getName(), updateSupervisorDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{supervisorId}")
    ResponseEntity<Void> deleteSupervisor(@PathVariable("supervisorId") int supervisorId) {
        if (supervisorService.deleteSupervisor(supervisorId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
