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

    private String theme;

    private boolean active;

    private int totalParticipants;

    private float avgTimeSpent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    @OneToMany(mappedBy = "project")
    private List<Flow> flows;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sharing_platform_id")
    private SharingPlatform sharingPlatform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;



    public Project() {
    }

    public Project(int id, String name, String theme, boolean active, int totalParticipants, float avgTimeSpent, Supervisor supervisor, Installation installation, List<Flow> flows, SharingPlatform sharingPlatform, Administrator administrator) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.active = active;
        this.totalParticipants = totalParticipants;
        this.avgTimeSpent = avgTimeSpent;
        this.supervisor = supervisor;
        this.installation = installation;
        this.flows = flows;
        this.sharingPlatform = sharingPlatform;
        this.administrator = administrator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void createFlow() {
        Flow flow = new Flow();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public float getAvgTimeSpent() {
        return avgTimeSpent;
    }

    public void setAvgTimeSpent(float avgTimeSpent) {
        this.avgTimeSpent = avgTimeSpent;
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

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Installation getInstallation() {
        return installation;
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
