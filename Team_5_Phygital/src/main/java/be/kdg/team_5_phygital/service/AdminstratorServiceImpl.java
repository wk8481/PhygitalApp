package be.kdg.team_5_phygital.service;
import be.kdg.team_5_phygital.domain.Administrator;
import be.kdg.team_5_phygital.repository.AdministratorRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminstratorServiceImpl implements AdministratorService{
    private AdministratorRepo administratorRepository;
    private Logger logger = LoggerFactory.getLogger(AdminstratorServiceImpl.class);

    @Autowired
    public AdminstratorServiceImpl(AdministratorRepo administratorRepository) {
        logger.info("AdministratorService is created");
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator addAdministrator(Administrator administrator) {

        return administratorRepository.save(administrator);
    }

    @Override
    public List<Administrator> getAdministrators() {
        return administratorRepository.findAll();
    }

    @Override
    public Optional<Administrator> getAdministratorById(int id){
        logger.info("Getting admin by id...");
        return administratorRepository.findById(id);
    }

}
