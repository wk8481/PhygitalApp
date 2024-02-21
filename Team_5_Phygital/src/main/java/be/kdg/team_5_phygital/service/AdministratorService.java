package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdministratorService {
    Administrator addAdministrator(Administrator administrator);

    List<Administrator> getAdministrators();

    Optional<Administrator> getAdministratorById(int id);
}
