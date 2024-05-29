package be.kdg.team_5_phygital.controller.api.dto;

public class NewSupervisorDto {
    private String name;
    private String email;
    private int sharingPlatformId;

    public NewSupervisorDto() {
    }

    @Override
    public String toString() {
        return "NewSupervisorDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sharingPlatformId=" + sharingPlatformId +
                '}';
    }

    public int getSharingPlatformId() {
        return sharingPlatformId;
    }

    public void setSharingPlatformId(int sharingPlatformId) {
        this.sharingPlatformId = sharingPlatformId;
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
