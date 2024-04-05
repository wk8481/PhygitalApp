package be.kdg.team_5_phygital.controller.api.dto;

public class NewSharingPlatformAdminDto {
    private String name;
    private String email;
    private String password;
    private int sharingPlatformId;

    public NewSharingPlatformAdminDto() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSharingPlatformId() {
        return sharingPlatformId;
    }

    public void setSharingPlatformId(int sharingPlatformId) {
        this.sharingPlatformId = sharingPlatformId;
    }
}
