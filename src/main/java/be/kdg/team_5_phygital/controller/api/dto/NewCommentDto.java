package be.kdg.team_5_phygital.controller.api.dto;

public class NewCommentDto {
    private String text;
    private int projectId;

    public NewCommentDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
