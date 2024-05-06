package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public Session createSession(Session session) {
        return sessionRepository.save(session);

    }
}
