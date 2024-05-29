package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.SubTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubThemeRepository extends JpaRepository<SubTheme, Integer> {
    List<SubTheme> getSubThemesByFlowId(int flowId);

    Optional<SubTheme> findByName(String name);
}
