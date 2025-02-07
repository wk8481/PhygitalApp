package be.kdg.team_5_phygital.controller.api.dto;

import be.kdg.team_5_phygital.domain.QuestionType;

public class QuestionDto {
    private int id;
    private String text;
    private QuestionType type;
    private boolean isVisible;

    public QuestionDto() {
    }

    public QuestionDto(int id, String text, QuestionType type, boolean isVisible) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.isVisible = isVisible;
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
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
