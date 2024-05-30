package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Answers;
import be.kdg.team_5_phygital.domain.Flow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answers, Integer> {
}
