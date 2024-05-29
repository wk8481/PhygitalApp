package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    Theme findByProjectId(int projectId);

    Optional<Theme> findByName(String name);
}
