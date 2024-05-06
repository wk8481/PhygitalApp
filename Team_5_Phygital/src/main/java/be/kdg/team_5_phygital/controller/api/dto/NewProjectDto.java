package be.kdg.team_5_phygital.controller.api.dto;

public class NewProjectDto {
    private String name;
    private String backgroundColorHex;
    private String fontName;
    private String logoPath;
    private boolean isPublic;
    private int sharingPlatformId;

    public NewProjectDto() {
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public int getSharingPlatformId() {
        return sharingPlatformId;
    }

    public void setSharingPlatformId(int sharingPlatformId) {
        this.sharingPlatformId = sharingPlatformId;
    }

    @Override
    public String toString() {
        return "NewProjectDto{" + "name='" + name + '\'' + ", backgroundColorHex='" + backgroundColorHex + '\'' + ", fontName='" + fontName + '\'' + ", logoPath='" + logoPath + '\'' + ", sharingPlatformId=" + sharingPlatformId + '}';
    }
}
