package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

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
    private SharingPlatform belongsTo;

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void createFlow() {
        Flow flow = new Flow();
    }
}
