package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateSharingPlatformDto {
    private String name;
    private String contactEmail;

    public UpdateSharingPlatformDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
