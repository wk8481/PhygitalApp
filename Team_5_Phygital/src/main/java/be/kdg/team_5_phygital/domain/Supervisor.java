package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "supervisor")
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "sharing_platform_id")
    private SharingPlatform sharingPlatform;

    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    private List<Project> projects;

    public Supervisor() {
    }

    public Supervisor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Supervisor(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Supervisor(String name, String email, Project project) {
        this.name = name;
        this.email = email;
        // no project entity?
    }

    public SharingPlatform getSharingPlatform() {
        return sharingPlatform;
    }

    public void setSharingPlatform(SharingPlatform sharingPlatform) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Supervisor(int id, String name, String email, String password, SharingPlatform sharingPlatform, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sharingPlatform = sharingPlatform;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Supervisor{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", sharingPlatform=" + sharingPlatform + ", projects=" + projects + '}';
    }
}
