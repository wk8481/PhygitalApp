package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.QuestionType;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.domain.Theme;
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
}
