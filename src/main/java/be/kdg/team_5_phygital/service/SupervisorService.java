package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.repository.SharingPlatformRepository;
import be.kdg.team_5_phygital.repository.SupervisorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {
    private final SupervisorRepository supervisorRepository;
    private final SharingPlatformRepository sharingPlatformRepository;

    public SupervisorService(SupervisorRepository supervisorRepository, SharingPlatformRepository sharingPlatformRepository) {
        this.supervisorRepository = supervisorRepository;
        this.sharingPlatformRepository = sharingPlatformRepository;
    }

    public Supervisor getSupervisor(int id) {
        return supervisorRepository.findById(id).orElse(null);
    }

    public Supervisor getSupervisorByName(String name) {
        return supervisorRepository.findByName(name).orElse(null);
    }

    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    public List<Supervisor> findSupervisorBySharingPlatform(SharingPlatform sharingPlatform) {
        return supervisorRepository.findSupervisorBySharingPlatformEquals(sharingPlatform);
    }

    @Transactional
    public Supervisor saveSupervisor(String name, String email, int sharingPlatformId) {
        SharingPlatform sharingPlatform = sharingPlatformRepository.findById(sharingPlatformId).orElse(null);
        return supervisorRepository.save(new Supervisor(name, email, sharingPlatform));
    }

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

    @Transactional
    public boolean deleteSupervisor(int supervisorId) {
        Optional<Supervisor> supervisor = supervisorRepository.findById(supervisorId);
        if (supervisor.isEmpty()) {
            return false;
        }
        supervisorRepository.deleteById(supervisorId);
        return true;
    }
}
