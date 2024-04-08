package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private boolean visible;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_theme_id")
    private SubTheme subTheme;

    public Question() {

    }

    public Question(String text, QuestionType type) {
        this.text = text;
        this.type = type;
    }

    public Question(String text, QuestionType type, SubTheme subTheme) {
        this.text = text;
        this.type = type;
        this.subTheme = subTheme;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public SubTheme getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(SubTheme subTheme) {
        this.subTheme = subTheme;
    }
}
