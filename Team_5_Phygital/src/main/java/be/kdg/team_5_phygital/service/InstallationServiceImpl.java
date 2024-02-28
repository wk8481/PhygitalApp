package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.repository.InstallationRepo;

import java.util.List;
import java.util.Optional;

public class InstallationServiceImpl implements InstallationService{
    private InstallationRepo installationRepository;

    public InstallationServiceImpl(InstallationRepo installationRepository) {
        this.installationRepository = installationRepository;
    }

    @Override
    public Installation createInstallation(Installation installation) {
        return installationRepository.save(installation);
    }

    @Override
    public Optional<Installation> getInstallationById(int id) {
        return installationRepository.findById(id);
    }

    @Override
    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }
}
