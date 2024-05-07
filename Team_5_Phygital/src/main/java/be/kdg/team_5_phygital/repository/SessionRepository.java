package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
}
