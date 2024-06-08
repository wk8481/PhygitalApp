package be.kdg.team_5_phygital.controller.api.dto;

public class ProjectDto {
    private int id;
    private String name;
    private String backgroundColorHex;
    private String fontName;
    private String logoUrl;
    private boolean isPublic;

    public ProjectDto() {
    }

    public ProjectDto(int id, String name, String backgroundColorHex, String fontName, String logoUrl, boolean isPublic) {
        this.id = id;
        this.name = name;
        this.backgroundColorHex = backgroundColorHex;
        this.fontName = fontName;
        this.logoUrl = logoUrl;
        this.isPublic = isPublic;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
