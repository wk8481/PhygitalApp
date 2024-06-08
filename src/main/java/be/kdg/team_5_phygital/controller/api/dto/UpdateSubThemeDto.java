package be.kdg.team_5_phygital.controller.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateSubThemeDto {
    private String name;
    private String information;
    private String mediaUrl;
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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    @JsonProperty("isVisible")
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
