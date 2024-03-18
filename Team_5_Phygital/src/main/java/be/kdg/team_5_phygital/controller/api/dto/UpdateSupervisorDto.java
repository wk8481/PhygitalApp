package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateSupervisorDto {
    private int id;
    private String name;

    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UpdateSupervisorDto() {
    }
}
