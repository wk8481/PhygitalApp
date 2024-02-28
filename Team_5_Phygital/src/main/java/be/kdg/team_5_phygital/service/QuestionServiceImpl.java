package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.repository.QuestionRepo;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private QuestionRepo questionRepository;

    public QuestionServiceImpl(QuestionRepo questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> getQuestionById(int id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
