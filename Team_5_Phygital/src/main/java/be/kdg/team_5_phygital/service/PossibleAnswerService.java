package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.PossibleAnswers;
import be.kdg.team_5_phygital.domain.Question;
import be.kdg.team_5_phygital.repository.PossibleAnswersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PossibleAnswerService {
    private final PossibleAnswersRepository possibleAnswersRepository;
    private final QuestionService questionService;


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
    public PossibleAnswers savePossibleAnswers(String text, int questionId) {
        PossibleAnswers answerExists = possibleAnswersRepository.findPossibleAnswersByAnswerEquals(text).orElse(null);
        if (answerExists != null) {
            return answerExists;
        }

        return possibleAnswersRepository.save(new PossibleAnswers(questionService.getQuestion(questionId), text));
    }

//    public boolean updatePossibleAnswers(int possibleAnswersId, String name) {
//        PossibleAnswers possibleAnswers = possibleAnswersRepository.findById(possibleAnswersId).orElse(null);
//        if (possibleAnswers == null) {
//            return false;
//        }
//        possibleAnswers.setName(name);
//        possibleAnswersRepository.save(possibleAnswers);
//        return true;
//    }

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

