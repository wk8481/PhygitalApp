package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("SUPERVISOR")
public class Supervisor extends User {
    @ManyToOne
    @JoinColumn(name = "sharing_platform_id")
    private SharingPlatform sharingPlatform;

    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    private List<Project> projects;

    public Supervisor() {
    }

    public Supervisor(String name, String email, SharingPlatform sharingPlatform) {
        super(name, email, UserRole.SUPERVISOR);
        this.sharingPlatform = sharingPlatform;
    }

    public Supervisor(String name, String email) {
        super(name, email, UserRole.SUPERVISOR);
    }

    public Supervisor(String name, String email, String password) {
        super(name, email, password, UserRole.SUPERVISOR);
    }

    public Supervisor(String name, String email, Project project) {
        super(name, email, UserRole.SUPERVISOR);
        // no project entity?
    }

    public SharingPlatform getSharingPlatform() {
        return sharingPlatform;
    }

    public void setSharingPlatform(SharingPlatform sharingPlatform) {
        this.sharingPlatform = sharingPlatform;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Supervisor(int id, String name, String email, String password, SharingPlatform sharingPlatform, List<Project> projects) {
        super(name, email, password, UserRole.SUPERVISOR);
        this.sharingPlatform = sharingPlatform;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "sharingPlatform=" + sharingPlatform +
                ", projects=" + projects +
                '}';
    }
}
