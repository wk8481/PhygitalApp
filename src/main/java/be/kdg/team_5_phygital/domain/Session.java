package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @ManyToOne
    private SubTheme subTheme;

    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
    private List<UserEmail> userEmails;

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

    public Session(int id) {
        this.id = id;
    }

    public Notes getNote() {
        return note;
    }

    public void setNote(Notes note) {
        this.note = note;
    }

    public Session() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<UserEmail> getUserEmails() {
        return userEmails;
    }

    public void setUserEmails(List<UserEmail> userEmails) {
        this.userEmails = userEmails;
    }

    public void addUserEmail(UserEmail userEmail){
        this.userEmails.add(userEmail);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", questions=" + questions +
                ", answers=" + answers +
                ", user=" + user.getName() +
                ", note=" + note.getNote() +
                '}';
    }
}
