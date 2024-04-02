package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.repository.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {
    private SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public Optional<Supervisor> getSupervisorById(int id) {
        return supervisorRepository.findById(id);
    }

    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    public List<Supervisor> findSupervisorBySharingPlatform(SharingPlatform sharingPlatform) { return supervisorRepository.findSupervisorBySharingPlatformEquals(sharingPlatform);}

    public boolean updateSupervisor(int supervisorId, String name, String email) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId).orElse(null);
        if (supervisor == null) {
            return false;
        }
        supervisor.setName(name);
        supervisor.setEmail(email);
        supervisorRepository.save(supervisor);
        return true;
    }
}
