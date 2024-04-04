package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.QuestionType;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;


    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }


    public Optional<Question> getQuestionById(int id) {
        return questionRepository.findById(id);
    }


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionBySubTheme(SubTheme subTheme){return questionRepository.getQuestionsBySubThemeEquals(subTheme);}

    @Transactional
    public Question saveQuestion(String text, QuestionType type) {
        return questionRepository.save(new Question(text, type));
    }

    public boolean updateQuestion(int questionId, String text, QuestionType type) {
        Question project = questionRepository.findById(questionId).orElse(null);
        if (project == null) {
            return false;
        }
        project.setText(text);
        project.setType(type);
        questionRepository.save(project);
        return true;
    }

    @Transactional
    public boolean deleteQuestion(int questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isEmpty()) {
            return false;
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
