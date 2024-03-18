package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateSubthemeDto {
    private int id;

    private String name;

    private String information;

    public UpdateSubthemeDto() {
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
