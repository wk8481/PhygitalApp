package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.repository.SupervisorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperVisorService {
    private SupervisorRepo supervisorRepo;

    public SuperVisorService(SupervisorRepo supervisorRepo) {
        this.supervisorRepo = supervisorRepo;
    }

    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepo.save(supervisor);
    }

    public Optional<Supervisor> getSupervisorById(int id) {
        return supervisorRepo.findById(id);
    }

    public List<Supervisor> getAllSupervisors() {
        return supervisorRepo.findAll();
    }

    public List<Supervisor> findSupervisorBySharingPlatform(SharingPlatform sharingPlatform) { return supervisorRepo.findSupervisorBySharingPlatformEquals(sharingPlatform);}

    public Supervisor updateSupervisor(Supervisor updatedSuperVisor) { return supervisorRepo.save(updatedSuperVisor
    );}
}
