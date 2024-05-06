package be.kdg.team_5_phygital.controller.api.dto;

import java.sql.Time;

public class NewAnswerDto {

    private String question;
    private String answer;
    private String userMail;
    private int subThemeId;
    private float durationSpend;

    public int getSubThemeId() {
        return subThemeId;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public float getDurationSpend() {
        return durationSpend;
    }

    public void setDurationSpend(float durationSpend) {
        this.durationSpend = durationSpend;
    }

    public void setSubThemeId(int subThemeId) {
        this.subThemeId = subThemeId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUsermail(String userMail) {
        this.userMail = userMail;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "NewAnswerDto{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
