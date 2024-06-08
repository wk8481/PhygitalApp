package be.kdg.team_5_phygital.controller.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateProjectDto {
    private String name;
    private String backgroundColorHex;
    private String fontName;
    private String logoUrl;
    @JsonProperty("isVisible")
    private boolean isVisible;

    public UpdateProjectDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColorHex() {
        return backgroundColorHex;
    }

    public void setBackgroundColorHex(String backgroundColorHex) {
        this.backgroundColorHex = backgroundColorHex;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public boolean isVisible() {
        return isVisible;
    }
    @JsonProperty("isVisible")
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }


    @Override
    public String toString() {
        return "UpdateProjectDto{" +
                "name='" + name + '\'' +
                ", backgroundColorHex='" + backgroundColorHex + '\'' +
                ", fontName='" + fontName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", isPublic=" + isVisible +
                '}';
    }
}
