package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "installation")
public class Installation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean isRunning;

    public Installation() {}

    public Installation(int id, boolean isRunning) {
        this.id = id;
        this.isRunning = isRunning;
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
}
