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

    @Transactional
    public Question saveQuestion(String text, QuestionType type, int subThemeId) {
        SubTheme subTheme = subThemeRepository.findById(subThemeId).orElse(null);
        return questionRepository.save(new Question(text, type, subTheme));
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

    public Optional<Question> getCurrentQuestion(int subThemeId, int currentIndex) {
        List<Question> questions = questionRepository.getQuestionsBySubThemeId(subThemeId);
        if (questions.isEmpty() || currentIndex < 0 || currentIndex >= questions.size()) {
            return Optional.empty();
        }
        return Optional.of(questions.get(currentIndex));
    }

    public Optional<Question> getNextQuestion(int subThemeId, int currentIndex, boolean isCircular) {
        List<Question> questions = questionRepository.getQuestionsBySubThemeId(subThemeId);
        if (questions.isEmpty() || currentIndex < 0 || currentIndex >= questions.size() - 1) {
            if (isCircular && !questions.isEmpty()) {
                return Optional.of(questions.get(0));
            }
            return Optional.empty();
        }
        return Optional.of(questions.get(currentIndex + 1));
    }

    public Optional<Question> getPreviousQuestion(int subThemeId, int currentIndex, boolean isCircular) {
        List<Question> questions = questionRepository.getQuestionsBySubThemeId(subThemeId);
        if (questions.isEmpty() || currentIndex <= 0 || currentIndex >= questions.size()) {
            if (isCircular && !questions.isEmpty()) {
                return Optional.of(questions.get(questions.size() - 1));
            }
            return Optional.empty();
        }
        return Optional.of(questions.get(currentIndex - 1));
    }
}
