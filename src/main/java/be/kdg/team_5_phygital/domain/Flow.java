package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flow")
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean isCircular;

    private int totalParticipants;

    private float totalTimeSpentInSec;

    private String info;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SubTheme> subThemes;

    public Flow() {
    }

    public Flow(String name, Project project) {
        this.name = name;
        this.project = project;
    }

    public Flow(String name, Project project, Installation installation) {
        this.name = name;
        this.project = project;
        this.installation = installation;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isCircular() {
        return isCircular;
    }

    public void setCircular(boolean circular) {
        isCircular = circular;
    }

    public float getTotalTimeSpentInSec() {
        return totalTimeSpentInSec;
    }

    public void setTotalTimeSpentInSec(float totalTimeSpentInSec) {
        this.totalTimeSpentInSec = totalTimeSpentInSec;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    public List<SubTheme> getSubThemes() {
        return subThemes;
    }

    public void setSubThemes(List<SubTheme> subThemes) {
        this.subThemes = subThemes;
    }

    public Flow(String name, boolean isCircular, Project project) {
        this.name = name;
        this.isCircular = isCircular;
        this.project = project;
    }
}
