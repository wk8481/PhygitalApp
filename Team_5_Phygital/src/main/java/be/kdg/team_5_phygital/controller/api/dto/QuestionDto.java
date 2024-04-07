package be.kdg.team_5_phygital.controller.api.dto;

import be.kdg.team_5_phygital.domain.QuestionType;

public class QuestionDto {
    private int id;
    private String text;
    private QuestionType type;

    public QuestionDto() {
    }

    public QuestionDto(int id, String text, QuestionType type) {
        this.id = id;
        this.text = text;
        this.type = type;
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

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
