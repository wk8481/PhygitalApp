package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    private static final Logger log = LoggerFactory.getLogger(SessionService.class);
    private final SessionRepository sessionRepository;


    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public Session createSession(Session session) {
        return sessionRepository.save(session);

    }

    public Optional<Session> getSessionOfUser(User user, SubTheme subTheme){

        return sessionRepository.findSessionByUserIsAndSubTheme(user, subTheme);
    }

    @Transactional
    public Session updateSessionAnswers(Session session, Answers answer){
       Session ans = session;
        log.error("Ans {}", ans);
        return null;
    }

    public Session getAnswersOfSession(Session session){
        return sessionRepository.getAnswersOfSession(session);
    }
    public Session getQuestionsOfSession(Session session){
        return sessionRepository.getQuestionsOfSession(session);
    }

    @Transactional
    public void addAnswerToSession(Session session, Answers answer){
        Session session1 = getAnswersOfSession(session);
        session1.getAnswers().add(answer);
        sessionRepository.save(session1);
    }

    @Transactional
    public void addQuestionToSession(Session session, Question question){
        Session session1 = getQuestionsOfSession(session);
        session1.getQuestions().add(question);
        sessionRepository.save(session1);
    }

    @Transactional
    public void updateTime(Session session){

        session.setTimestamp(LocalDateTime.now());
        sessionRepository.save(session);
    }

    public List<Session> getSessions(SubTheme subTheme){
        return sessionRepository.getSessionsBySubTheme(subTheme);
    }

    public List<Session> getQuestionsOfSessions(List<Session> sessions){
        return sessionRepository.getQuestionsOfSessions(sessions);
    }


    public List<Session> getAnswersOfSessions(List<Session> sessions){
        return sessionRepository.getAnswersOfSessions(sessions);
    }
}
