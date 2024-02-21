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

    public Project() {
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
}
