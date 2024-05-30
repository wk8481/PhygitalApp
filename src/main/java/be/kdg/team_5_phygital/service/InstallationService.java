package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.util.Location;
import be.kdg.team_5_phygital.repository.InstallationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {
    private final InstallationRepository installationRepository;

    public InstallationService(InstallationRepository installationRepository) {
        this.installationRepository = installationRepository;
    }


    public Installation getInstallation(int id) {
        return installationRepository.findById(id).orElse(null);
    }


    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }

    @Transactional
    public Installation saveInstallation(String name, String province, String city, String street, int streetNumber) {
        Location location = new Location(province, city, street, streetNumber);
        Installation installation = new Installation(name, location);
        return installationRepository.save(installation);
    }

    public boolean updateInstallation(int installationId, String name, String province, String city, String street, int streetNumber) {
        Installation installation = installationRepository.findById(installationId).orElse(null);
        if (installation == null) {
            return false;
        }
        installation.setName(name);
        installation.getLocation().setProvince(province);
        installation.getLocation().setCity(city);
        installation.getLocation().setStreet(street);
        installation.getLocation().setStreetNumber(streetNumber);
        installationRepository.save(installation);
        return true;
    }

    @Transactional
    public boolean deleteInstallation(int installationId) {
        Optional<Installation> installation = installationRepository.findById(installationId);
        if (installation.isEmpty()) {
            return false;
        }
        installationRepository.deleteById(installationId);
        return true;
    }
}
