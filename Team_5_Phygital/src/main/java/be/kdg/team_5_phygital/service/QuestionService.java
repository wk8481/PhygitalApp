package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.repository.QuestionRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private QuestionRepo questionRepository;

    public QuestionService(QuestionRepo questionRepository) {
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

    public Question updateQuestion(Question update) {return questionRepository.save(update);}
}
