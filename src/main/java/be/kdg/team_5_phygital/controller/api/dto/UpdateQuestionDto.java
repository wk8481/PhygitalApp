package be.kdg.team_5_phygital.controller.api.dto;

import be.kdg.team_5_phygital.domain.QuestionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UpdateQuestionDto {
    private String text;
    private QuestionType type;
    @JsonProperty("isVisible")
    private boolean isVisible;
    private List<String> answers;

    public UpdateQuestionDto() {
    }

    public List<String> getAnswers() {
        return answers;
    }

    public UpdateQuestionDto(String text, QuestionType type, boolean isVisible, List<String> answers) {
        this.text = text;
        this.type = type;
        this.isVisible = isVisible;
        this.answers = answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
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

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
