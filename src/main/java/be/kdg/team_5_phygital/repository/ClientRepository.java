package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByName(String name);

}
