package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    private QuestionType type;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinTable(name = "subtheme")
    private SubTheme subTheme;

    public Question(int id, String text, QuestionType type, SubTheme theme) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.subTheme = theme;
    }

    public Question() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
