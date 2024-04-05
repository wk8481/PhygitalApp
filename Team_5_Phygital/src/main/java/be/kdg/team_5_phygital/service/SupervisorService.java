package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Supervisor;
import be.kdg.team_5_phygital.domain.Theme;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import be.kdg.team_5_phygital.repository.SupervisorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {
    private final SupervisorRepository supervisorRepository;
    private final ProjectRepository projectRepository;

    public SupervisorService(SupervisorRepository supervisorRepository, ProjectRepository projectRepository) {
        this.supervisorRepository = supervisorRepository;
        this.projectRepository = projectRepository;
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

    @Transactional
    public Supervisor saveSupervisor(String name, String email, int projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return supervisorRepository.save(new Supervisor(name, email, project));
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
