package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateSupervisorDto {
    private String name;
    private String email;

    public UpdateSupervisorDto() {
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

    @Override
    public String toString() {
        return "UpdateSupervisorDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
