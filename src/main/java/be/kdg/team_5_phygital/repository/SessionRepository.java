package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {


    @Query("SELECT s FROM Session s WHERE s.user = :user AND s.subTheme = :subTheme AND s.timestamp >= :oneHourAgo")
    Optional<Session> findSessionByUserAndSubThemeWithinLastHour(@Param("user") User user, @Param("subTheme") SubTheme subTheme, @Param("oneHourAgo") LocalDateTime oneHourAgo);

    @Query("FROM Session s LEFT JOIN FETCH s.answers WHERE s = :session")
    Session getAnswersOfSession(@Param("session") Session session);

    @Query("FROM Session s LEFT JOIN FETCH s.questions WHERE s = :session")
    Session getQuestionsOfSession(@Param("session") Session session);


    List<Session> getSessionsBySubTheme(@Param("subTheme") SubTheme subTheme);

    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.answers WHERE s IN :sessions")
    List<Session> getAnswersOfSessions(@Param("sessions") List<Session> sessions);


    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.questions WHERE s IN :sessions")
    List<Session> getQuestionsOfSessions(@Param("sessions") List<Session> sessions);

    Session findTopByOrderByTimestampDesc();
}
