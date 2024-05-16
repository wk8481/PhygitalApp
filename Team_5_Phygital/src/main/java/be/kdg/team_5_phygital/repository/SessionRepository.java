package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    Optional<Session> findSessionByUserIs(@Param("user") User user);

    @Query("FROM Session s LEFT JOIN FETCH s.answers WHERE s = :session")
    Session getAnswersOfSession(@Param("session") Session session);

    @Query("FROM Session s LEFT JOIN FETCH s.questions WHERE s = :session")
    Session getQuestionsOfSession(@Param("session") Session session);


    List<Session> getSessionsBySubTheme(@Param("subTheme") SubTheme subTheme);

    @Query("SELECT DISTINCT s FROM Session s LEFT JOIN FETCH s.answers WHERE s IN :sessions")
    List<Session> getAnswersOfSessions(@Param("sessions") List<Session> sessions);


    @Query("SELECT DISTINCT s FROM Session s LEFT JOIN FETCH s.questions WHERE s IN :sessions")
    List<Session> getQuestionsOfSessions(@Param("sessions") List<Session> sessions);
}
