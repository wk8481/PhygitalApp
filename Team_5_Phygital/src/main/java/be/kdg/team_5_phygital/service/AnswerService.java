package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.repository.AnswerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Answers saveAnswer(User user, LocalDateTime timestamp, Question question, String answer) {
        return answerRepository.save(new Answers(user, timestamp, question, answer));
    }
}
