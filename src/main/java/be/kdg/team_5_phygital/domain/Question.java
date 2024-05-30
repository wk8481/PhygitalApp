package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private boolean isVisible;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_theme_id")
    private SubTheme subTheme;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<PossibleAnswers> possibleAnswers;

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

    public List<PossibleAnswers> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<PossibleAnswers> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public SubTheme getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(SubTheme subTheme) {
        this.subTheme = subTheme;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                '}';
    }
}
