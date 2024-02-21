package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.WebApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebApplicationRepo extends JpaRepository<WebApplication, Integer> {
}
