package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Supervisor;

import java.util.List;
import java.util.Optional;

public interface SupervisorService {

    Supervisor createSupervisor(Supervisor supervisor);

    Optional<Supervisor> getSupervisorById(int id);

    List<Supervisor> getAllSupervisors();

    List<Supervisor> getAllSupervisorsOfCompany(int sharingPlatformId);
}
