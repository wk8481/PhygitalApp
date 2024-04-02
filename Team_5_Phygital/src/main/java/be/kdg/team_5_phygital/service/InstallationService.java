package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.repository.InstallationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {
    private InstallationRepository installationRepository;

    public InstallationService(InstallationRepository installationRepository) {
        this.installationRepository = installationRepository;
    }


    public Installation createInstallation(Installation installation) {
        return installationRepository.save(installation);
    }


    public Optional<Installation> getInstallationById(int id) {
        return installationRepository.findById(id);
    }


    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }
}
