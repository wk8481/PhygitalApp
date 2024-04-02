package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.SubTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsBySubThemeEquals(SubTheme subTheme);

    Optional<Question> findByText(String text);
}
