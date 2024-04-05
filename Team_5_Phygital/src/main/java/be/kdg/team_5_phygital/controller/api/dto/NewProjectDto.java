package be.kdg.team_5_phygital.controller.api.dto;

public class NewProjectDto {
    private String name;
    private int sharingPlatformId;

    public NewProjectDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSharingPlatformId() {
        return sharingPlatformId;
    }

    public void setSharingPlatformId(int sharingPlatformId) {
        this.sharingPlatformId = sharingPlatformId;
    }
}
