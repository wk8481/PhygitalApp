package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SharingPlatformRepository extends JpaRepository<SharingPlatform, Integer> {
    Optional<SharingPlatform> findByName(String name);
}
