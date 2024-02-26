package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Installation;

import java.util.List;
import java.util.Optional;

public interface InstallationService {

    Installation createInstallation(Installation installation);

    Optional<Installation> getInstallationById(int id);

    List<Installation> getAllInstallations();
}
