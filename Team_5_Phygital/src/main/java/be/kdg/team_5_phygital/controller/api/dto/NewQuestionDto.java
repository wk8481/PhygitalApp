package be.kdg.team_5_phygital.controller.api.dto;

import be.kdg.team_5_phygital.domain.QuestionType;

public class NewQuestionDto {
    private String text;
    private QuestionType type;
    private int subThemeId;

    public NewQuestionDto() {
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

    public int getSubThemeId() {
        return subThemeId;
    }

    public void setSubThemeId(int subThemeId) {
        this.subThemeId = subThemeId;
    }

    @Override
    public String toString() {
        return "NewQuestionDto{" +
                "text='" + text + '\'' +
                ", type=" + type +
                ", subThemeId=" + subThemeId +
                '}';
    }
}
