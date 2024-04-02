package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.SharingPlatformAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SharingPlatformAdminRepository extends JpaRepository<SharingPlatformAdmin, Integer> {
    Optional<SharingPlatformAdmin> findByName(String name);

}
