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
//
//    @ManyToOne
//    private User user;
//
//    private LocalDateTime timestamp;
//
//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;

    private String answers;


    public Answers() {

    }

//    public Answers(User user, LocalDateTime timestamp, Question question, String answers) {
//        this.user = user;
//        this.timestamp = timestamp;
//        this.question = question;
//        this.answers = answers;
//    }


    public Answers(String answers) {
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestions(Question question) {
//        this.question = question;
//    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "answers='" + answers + '\'' +
                '}';
    }
}
