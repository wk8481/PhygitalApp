package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "answer")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    private LocalDateTime timestamp;

    private String questions;

    private String answers;

    @OneToOne
    private SubTheme subtheme;

    public Answers(User user, LocalDateTime timestamp, String questions, String answers, SubTheme subtheme) {
        this.user = user;
        this.timestamp = timestamp;
        this.questions = questions;
        this.answers = answers;
        this.subtheme = subtheme;
    }

    public Answers() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public SubTheme getSubtheme() {
        return subtheme;
    }

    public void setSubtheme(SubTheme subtheme) {
        this.subtheme = subtheme;
    }
}
