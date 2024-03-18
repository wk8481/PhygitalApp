package be.kdg.team_5_phygital.controller.api.dto;

import be.kdg.team_5_phygital.domain.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UpdateQuestionDto {
    private int id;

    private String text;

    private QuestionType type;


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

    public UpdateQuestionDto() {


    }
}
