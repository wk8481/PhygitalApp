package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;

    private LocalDateTime timestamp;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Question> questions;

    @ManyToMany()
    @JoinTable(name = "session_question",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;

    @ManyToMany()
    @JoinTable(name = "session_answer",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answers> answers;

    @ManyToOne
    private User user;

    @OneToOne
    private Notes note;

    @OneToOne
    private SubTheme subTheme;

    public SubTheme getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(SubTheme subTheme) {
        this.subTheme = subTheme;
    }

    public Session(LocalDateTime timestamp, List<Question> questions, List<Answers> answers, User user, Notes note, SubTheme subTheme) {
        this.timestamp = timestamp;
        this.questions = questions;
        this.answers = answers;
        this.user = user;
        this.note = note;
        this.subTheme = subTheme;
    }

    public Session(int sessionId) {
        this.sessionId = sessionId;
    }

    public Notes getNote() {
        return note;
    }

    public void setNote(Notes note) {
        this.note = note;
    }

    public Session() {

    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", questions=" + questions +
                ", answers=" + answers +
                ", user=" + user +
                ", note=" + note +
                '}';
    }
}
