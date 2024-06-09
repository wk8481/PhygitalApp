package be.kdg.team_5_phygital.controller.api.dto;

public class SharingPlatformDto {
    private int id;
    private String name;
    private String contactEmail;
    private String logoUrl;


    public SharingPlatformDto() {
    }

    public SharingPlatformDto(int id, String name, String contactEmail, String logoUrl) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.logoUrl = logoUrl;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
