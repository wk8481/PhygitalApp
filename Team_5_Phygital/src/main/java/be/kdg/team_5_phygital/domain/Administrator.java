package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "administrator")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "administrator")
    private List<SharingPlatform> sharingPlatforms;
    @OneToMany(mappedBy = "administrator")
    private List<Project> projects;

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
}
