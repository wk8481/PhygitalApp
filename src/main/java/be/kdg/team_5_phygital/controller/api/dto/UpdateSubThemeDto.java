package be.kdg.team_5_phygital.controller.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateSubThemeDto {
    private String name;
    private String information;
    private boolean isVisible;

    public UpdateSubThemeDto() {
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

    @JsonProperty("isVisible")
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public String toString() {
        return "UpdateSubThemeDto{" +
                "name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", isVisible=" + isVisible +
                '}';
    }
}
