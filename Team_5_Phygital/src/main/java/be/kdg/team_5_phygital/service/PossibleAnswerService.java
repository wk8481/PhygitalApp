package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.PossibleAnswers;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.repository.PossibleAnswersRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PossibleAnswerService {
    private final PossibleAnswersRepository possibleAnswersRepository;
    private final QuestionService questionService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public PossibleAnswerService(PossibleAnswersRepository possibleAnswersRepository, QuestionService questionService) {
        this.possibleAnswersRepository = possibleAnswersRepository;
        this.questionService = questionService;
    }

    public PossibleAnswers getPossibleAnswers(int possibleAnswersId) {
        return possibleAnswersRepository.findById(possibleAnswersId).orElse(null);
    }


    public List<PossibleAnswers> getAllPossibleAnswers() {
        return possibleAnswersRepository.findAll();
    }

    public List<PossibleAnswers> getPossibleAnswersByQuestionId(List<Question> question) {
        return possibleAnswersRepository.findPossibleAnswersByQuestion(question);
    }

    @Transactional
    public void savePossibleAnswers(String text, Question question) {
        PossibleAnswers answer = new PossibleAnswers(text, question);
        possibleAnswersRepository.save(answer);
    }

    public boolean updatePossibleAnswers(String answer, Question question) {
        PossibleAnswers possibleAnswers = possibleAnswersRepository.findPossibleAnswersByAnswerAndQuestion(answer, question);
        if (possibleAnswers == null) {
            return false;
        }
        possibleAnswers.setAnswer(answer);
        possibleAnswers.setQuestion(question);
        possibleAnswersRepository.save(possibleAnswers);
        return true;
    }

    @Transactional
    public boolean deletePossibleAnswers(int possibleAnswersId) {
        Optional<PossibleAnswers> possibleAnswers = possibleAnswersRepository.findById(possibleAnswersId);
        if (possibleAnswers.isEmpty()) {
            return false;
        }
        possibleAnswersRepository.deleteById(possibleAnswersId);
        return true;
    }
}

