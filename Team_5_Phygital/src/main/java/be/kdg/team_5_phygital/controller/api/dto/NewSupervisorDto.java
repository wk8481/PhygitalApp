package be.kdg.team_5_phygital.controller.api.dto;

public class NewSupervisorDto {
    private String name;
    private String email;
    private int projectId;

    public NewSupervisorDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
