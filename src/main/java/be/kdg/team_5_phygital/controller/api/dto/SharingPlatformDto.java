package be.kdg.team_5_phygital.controller.api.dto;

public class SharingPlatformDto {
    private int id;
    private String name;
    private String contactEmail;
    private String logoPath;


    public SharingPlatformDto() {
    }

    public SharingPlatformDto(int id, String name, String contactEmail, String logoPath) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
