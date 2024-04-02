package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateSharingPlatformAdminDto {
    private String name;
    private String email;

    public UpdateSharingPlatformAdminDto() {
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
}
