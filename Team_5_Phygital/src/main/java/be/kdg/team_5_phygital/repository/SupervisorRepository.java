package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import be.kdg.team_5_phygital.domain.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

    List<Supervisor> findSupervisorBySharingPlatformEquals(SharingPlatform sharingPlatform);
}
