package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Administrator;
import be.kdg.team_5_phygital.repository.AdministratorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public Administrator getAdministrator(int administratorId) {
        return administratorRepository.findById(administratorId).orElse(null);
    }

    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    @Transactional
    public Administrator saveAdministrator(String name) {
        return administratorRepository.save(new Administrator(name));
    }
}
