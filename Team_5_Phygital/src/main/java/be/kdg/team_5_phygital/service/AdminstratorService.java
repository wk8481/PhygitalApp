package be.kdg.team_5_phygital.service;
import be.kdg.team_5_phygital.domain.Administrator;
import be.kdg.team_5_phygital.repository.AdministratorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminstratorService {
    private AdministratorRepository administratorRepository;
    private Logger logger = LoggerFactory.getLogger(AdminstratorService.class);

    @Autowired
    public AdminstratorService(AdministratorRepository administratorRepository) {
        logger.info("AdministratorService is created");
        this.administratorRepository = administratorRepository;
    }


    public Administrator addAdministrator(Administrator administrator) {

        return administratorRepository.save(administrator);
    }


    public List<Administrator> getAdministrators() {
        return administratorRepository.findAll();
    }


    public Optional<Administrator> getAdministratorById(int id){
        logger.info("Getting admin by id...");
        return administratorRepository.findById(id);
    }

}
