package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.domain.util.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
