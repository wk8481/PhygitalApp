package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Installation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstallationRepository extends JpaRepository<Installation, Integer> {
    Optional<Installation> findByName(String name);
}
