package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Installation;
import be.kdg.team_5_phygital.domain.PossibleAnswers;
import be.kdg.team_5_phygital.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PossibleAnswersRepository extends JpaRepository<PossibleAnswers, Integer> {

    PossibleAnswers findPossibleAnswersByAnswerAndQuestion(String text, Question question);
    @Query("SELECT a FROM PossibleAnswers a WHERE a.question IN :question")
    List<PossibleAnswers> findPossibleAnswersByQuestion(@Param("question") List<Question> question);

    Optional<PossibleAnswers> findPossibleAnswersByAnswerEquals(String text);
}
