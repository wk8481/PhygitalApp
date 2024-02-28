package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
}
