package be.kdg.team_5_phygital.controller.api.dto;

public class NewFlowDto {
    private String name;
    private int projectId;
    private int installationId;

    public NewFlowDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getInstallationId() {
        return installationId;
    }

    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }
}
