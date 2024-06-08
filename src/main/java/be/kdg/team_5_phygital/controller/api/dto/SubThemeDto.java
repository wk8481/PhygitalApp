package be.kdg.team_5_phygital.controller.api.dto;

public class SubThemeDto {
    private int id;
    private String name;
    private String information;
    private boolean isVisible;
    private String mediaUrl;


    public SubThemeDto() {
    }

    public SubThemeDto(int id, String name, String information, boolean isVisible, String mediaUrl) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.isVisible = isVisible;
        this.mediaUrl = mediaUrl;
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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
