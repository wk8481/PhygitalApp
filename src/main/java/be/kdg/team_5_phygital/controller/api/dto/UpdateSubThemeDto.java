package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateSubThemeDto {
    private String name;
    private String information;
    private boolean isVisible;
    private String mediaUrl;

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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
