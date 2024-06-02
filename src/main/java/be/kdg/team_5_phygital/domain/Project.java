package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String backgroundColorHex;

    private String fontName;

    private String logoPath;

    private boolean isActive;

    private boolean isPublic;

    private int totalParticipants;

    private float totalTimeSpentInSec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    @OneToMany(mappedBy = "project")
    private List<Flow> flows;

    @ManyToOne
    @JoinColumn(name = "sharing_platform_id")
    private SharingPlatform sharingPlatform;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @OneToMany(mappedBy = "project")
    private List<Comment> comments;

    public Project() {
    }

    public Project(String name, SharingPlatform sharingPlatform) {
        this.name = name;
        this.backgroundColorHex = "#FFFFFF";
        this.sharingPlatform = sharingPlatform;
    }

    public Project(String name, String backgroundColorHex, String fontName, SharingPlatform sharingPlatform) {
        this.name = name;
        this.backgroundColorHex = backgroundColorHex;
        this.fontName = fontName;
        this.sharingPlatform = sharingPlatform;
    }

    public Project(String name, String backgroundColorHex, String fontName, String logoPath, SharingPlatform sharingPlatform) {
        this.name = name;
        this.backgroundColorHex = backgroundColorHex;
        this.fontName = fontName;
        this.logoPath = logoPath;
        this.sharingPlatform = sharingPlatform;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public float getTotalTimeSpentInSec() {
        return totalTimeSpentInSec;
    }

    public void setTotalTimeSpentInSec(float totalTimeSpent) {
        this.totalTimeSpentInSec = totalTimeSpent;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    public SharingPlatform getSharingPlatform() {
        return sharingPlatform;
    }

    public void setSharingPlatform(SharingPlatform sharingPlatform) {
        this.sharingPlatform = sharingPlatform;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalParticipants=" + totalParticipants +
                ", totalTimeSpent=" + totalTimeSpentInSec +
                '}';
    }
}
