package be.kdg.team_5_phygital.controller.api.dto;

public class ProjectDto {
    private int id;
    private String name;

    public ProjectDto() {
    }

    public ProjectDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
