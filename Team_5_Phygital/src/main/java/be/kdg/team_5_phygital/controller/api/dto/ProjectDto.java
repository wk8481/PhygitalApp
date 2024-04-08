package be.kdg.team_5_phygital.controller.api.dto;

public class ProjectDto {
    private int id;
    private String name;
    private String backgroundColorHex;
    private String fontName;
    private String logoPath;

    public ProjectDto() {
    }

    public ProjectDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProjectDto(int id, String name, String backgroundColorHex, String fontName, String logoPath) {
        this.id = id;
        this.name = name;
        this.backgroundColorHex = backgroundColorHex;
        this.fontName = fontName;
        this.logoPath = logoPath;
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
