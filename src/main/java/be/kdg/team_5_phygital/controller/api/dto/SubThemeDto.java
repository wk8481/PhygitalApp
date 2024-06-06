package be.kdg.team_5_phygital.controller.api.dto;

public class SubThemeDto {
    private int id;
    private String name;
    private String information;
    private boolean isVisible;

    public SubThemeDto() {
    }

    public SubThemeDto(int id, String name, String information, boolean isVisible) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.isVisible = isVisible;
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
