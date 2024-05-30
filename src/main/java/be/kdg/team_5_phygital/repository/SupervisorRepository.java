package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    List<Supervisor> findSupervisorBySharingPlatformEquals(SharingPlatform sharingPlatform);

    Optional<Supervisor> findByName(String name);
}
