package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String text;

    private QuestionType questionType;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinTable(name = "subtheme")
    private Subtheme subtheme;

    public Question(int questionId, String text, QuestionType questionType) {
        this.questionId = questionId;
        this.text = text;
        this.questionType = questionType;
    }

    public Question() {

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
