package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Administrator;
import be.kdg.team_5_phygital.repository.AdministratorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    private final AdministratorRepository administratorRepository;
    private final Logger logger = LoggerFactory.getLogger(AdministratorService.class);

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public Administrator addAdministrator(Administrator administrator) {

        return administratorRepository.save(administrator);
    }

    public List<Administrator> getAdministrators() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> getAdministratorById(int id) {
        logger.info("Getting admin by id...");
        return administratorRepository.findById(id);
    }
}
