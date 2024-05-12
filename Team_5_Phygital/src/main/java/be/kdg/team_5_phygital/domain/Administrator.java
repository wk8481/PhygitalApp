package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrator extends User {

    @OneToMany(mappedBy = "administrator")
    private List<SharingPlatform> sharingPlatforms;
    @OneToMany(mappedBy = "administrator")
    private List<Project> projects;

    public Administrator() {
    }

    public Administrator(String name) {
        super(name);
    }

    public Administrator(String name, String email, String password, List<SharingPlatform> sharingPlatforms) {
        super(name, email, password, UserRole.ADMIN);
        this.sharingPlatforms = sharingPlatforms;
    }

    public Administrator(String name, List<SharingPlatform> sharingPlatforms, List<Project> projects) {
        super(name);
        this.sharingPlatforms = sharingPlatforms;
        this.projects = projects;
    }

    public Administrator(String name, String email, String password, List<SharingPlatform> sharingPlatforms, List<Project> projects) {
        super(name, email, password, UserRole.ADMIN);
        this.sharingPlatforms = sharingPlatforms;
        this.projects = projects;
    }

    public List<SharingPlatform> getSharingPlatforms() {
        return sharingPlatforms;
    }

    public void setSharingPlatforms(List<SharingPlatform> sharingPlatforms) {
        this.sharingPlatforms = sharingPlatforms;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "sharingPlatforms=" + sharingPlatforms +
                ", projects=" + projects +
                '}';
    }
}
