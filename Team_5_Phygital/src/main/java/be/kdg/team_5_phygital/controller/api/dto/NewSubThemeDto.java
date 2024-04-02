package be.kdg.team_5_phygital.controller.api.dto;

public class NewSubThemeDto {
    private String name;

    private String information;

    public NewSubThemeDto() {
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
