package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.SharingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SharingPlatformRepository extends JpaRepository<SharingPlatform, Integer> {
    Optional<SharingPlatform> findByName(String name);


    @Transactional
    @Modifying
    @Query("UPDATE SharingPlatform e SET e.totalTimeSpentInSec = e.totalTimeSpentInSec + :timeToAdd, e.totalParticipants = e.totalParticipants + 1 WHERE e.id = :platformId")
    void updatePlatformTimeAndParticipants(@Param("platformId") int platformId, @Param("timeToAdd") float timeToAdd);
}
