package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.repository.SupervisorRepo;

import java.util.List;
import java.util.Optional;

public class SuperVisorServiceImpl implements SupervisorService{
    private SupervisorRepo supervisorRepo;

    public SuperVisorServiceImpl(SupervisorRepo supervisorRepo) {
        this.supervisorRepo = supervisorRepo;
    }

    @Override
    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepo.save(supervisor);
    }

    @Override
    public Optional<Supervisor> getSupervisorById(int id) {
        return supervisorRepo.findById(id);
    }

    @Override
    public List<Supervisor> getAllSupervisors() {
        return supervisorRepo.findAll();
    }

    @Override
    public List<Supervisor> getAllSupervisorsOfCompany(int sharingPlatformId) {
        return null /*supervisorRepo.findAllBySharingPlatformId*/;
    }
}
