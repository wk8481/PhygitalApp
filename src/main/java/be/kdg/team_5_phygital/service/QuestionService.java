package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.repository.PossibleAnswersRepository;
import be.kdg.team_5_phygital.repository.QuestionRepository;
import be.kdg.team_5_phygital.repository.SessionRepository;
import be.kdg.team_5_phygital.repository.SubThemeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final SubThemeRepository subThemeRepository;
    private final PossibleAnswersRepository possibleAnswersRepository;
    private final SessionRepository sessionRepository;

    public QuestionService(QuestionRepository questionRepository, SubThemeRepository subThemeRepository, PossibleAnswersRepository possibleAnswersRepository, SessionRepository sessionRepository) {
        this.questionRepository = questionRepository;
        this.subThemeRepository = subThemeRepository;
        this.possibleAnswersRepository = possibleAnswersRepository;
        this.sessionRepository = sessionRepository;
    }

    public Question getQuestion(int questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    public Question getQuestionByText(String text) {
        return questionRepository.findByText(text).orElse(null);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsBySubTheme(SubTheme subTheme) {
        return questionRepository.getQuestionsBySubThemeEquals(subTheme);
    }

    public List<Question> getQuestionsBySubThemeAndVisible(SubTheme subTheme) {
        return questionRepository.getQuestionsBySubThemeEqualsAndIsVisibleIsTrue(subTheme);
    }

    @Transactional
    public Question saveQuestion(String text, QuestionType type, int subThemeId) {
        SubTheme subTheme = subThemeRepository.findById(subThemeId).orElse(null);
        return questionRepository.save(new Question(text, type, true, subTheme));
    }

    public boolean updateQuestion(int questionId, String text, QuestionType type, boolean isVisible) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question == null) {
            return false;
        }
        question.setText(text);
        question.setType(type);
        question.setVisible(isVisible);
        questionRepository.save(question);
        return true;
    }

    @Transactional
    public boolean deleteQuestion(int questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isEmpty()) {
            return false;
        }
        for (PossibleAnswers possibleAnswer : question.get().getPossibleAnswers()) {
            possibleAnswersRepository.deleteById(possibleAnswer.getId());
        }
        List<Session> sessions = sessionRepository.findAll();
        for (Session session : sessions) {
            session.getQuestions().remove(question.orElse(null));
        }
        questionRepository.deleteById(questionId);
        return true;
    }
}
