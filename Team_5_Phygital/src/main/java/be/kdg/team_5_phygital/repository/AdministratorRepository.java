package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Optional<Administrator> findByName(String name);
}
