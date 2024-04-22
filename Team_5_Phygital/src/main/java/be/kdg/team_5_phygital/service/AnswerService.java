package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Answers;
import be.kdg.team_5_phygital.domain.SubTheme;
import be.kdg.team_5_phygital.domain.User;
import be.kdg.team_5_phygital.domain.UserRole;
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
    public Answers saveAnswer(User user, LocalDateTime timestamp, String question, String answer, SubTheme subtheme) {
        return answerRepository.save(new Answers(user, timestamp, question, answer, subtheme));
    }
}
