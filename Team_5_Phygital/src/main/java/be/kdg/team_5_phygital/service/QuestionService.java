package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Question addQuestion(Question question);

    Optional<Question> getQuestionById(int id);

    List<Question> getAllQuestions();
}
