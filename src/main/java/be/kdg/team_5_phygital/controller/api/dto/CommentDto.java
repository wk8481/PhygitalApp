package be.kdg.team_5_phygital.controller.api.dto;

public class CommentDto {
    private int id;
    private String text;

    public CommentDto() {
    }

    public CommentDto(int id, String text) {
        this.id = id;
        this.text = text;
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
}
