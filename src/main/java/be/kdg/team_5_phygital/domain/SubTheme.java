package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_theme")
public class SubTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String information;

    private String mediaUrl;

    private boolean isVisible;

    @ManyToOne
    @JoinColumn(name = "flow_id")
    private Flow flow;

    public SubTheme() {
    }

    public SubTheme(String name, String information) {
        this.name = name;
        this.information = information;
    }

    public SubTheme(String name, String information, Flow flow) {
        this.name = name;
        this.information = information;
        this.flow = flow;
    }

    public SubTheme(String testSubtheme) {
        this.name = testSubtheme;
        this.information = "test";
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    private int currentIndex;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public boolean isCircularFlow() {
        return flow != null && flow.isCircular();
    }
}
