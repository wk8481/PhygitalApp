package be.kdg.team_5_phygital.domain;

import be.kdg.team_5_phygital.domain.util.Location;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "installation")
public class Installation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean isRunning;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "installation")
    private List<Flow> flows;

    public Installation() {}

    public Installation(String name) {
        this.name = name;
    }

    public Installation(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Installation(boolean isRunning, User user) {
        this.isRunning = isRunning;
        this.user = user;
    }

    public Installation(String name, boolean isRunning) {
        this.name = name;
        this.isRunning = isRunning;
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

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
