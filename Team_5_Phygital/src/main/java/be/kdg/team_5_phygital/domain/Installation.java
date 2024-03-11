package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "installation")
public class Installation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isRunning;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDetail user;

    @OneToMany(mappedBy = "installation")
    private List<Project> projects;

    public Installation() {}

    public Installation(boolean isRunning, UserDetail user) {
        this.isRunning = isRunning;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
