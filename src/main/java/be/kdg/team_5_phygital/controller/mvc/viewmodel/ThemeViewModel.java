package be.kdg.team_5_phygital.controller.mvc.viewmodel;

public class ThemeViewModel {
    private int id;
    private String name;
    private String information;
    private String mediaUrl;

    public ThemeViewModel() {
    }

    public ThemeViewModel(int id, String name, String information, String mediaUrl) {
        this.id = id;
        this.name = name;
        this.information = information;
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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
