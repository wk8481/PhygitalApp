package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SharingPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String contactEmail;

    private String logoPath;

    private int totalParticipants;

    private float totalTimeSpentInSec;

    @ManyToOne()
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @OneToMany(mappedBy = "sharingPlatform")
    private List<Project> projects;

    @OneToMany(mappedBy = "sharingPlatform")
    private List<Supervisor> supervisors;

    @OneToOne(mappedBy = "sharingPlatform")
    private Client client;


    public SharingPlatform() {}

    public SharingPlatform(String name) {
        this.name = name;
    }

    public SharingPlatform(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public SharingPlatform(String name, String contactEmail, String logoPath) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.logoPath = logoPath;
    }

    public SharingPlatform(String name, String contactEmail, int totalParticipants) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.totalParticipants = totalParticipants;
    }

    public int getId() {
        return id;
    }

    public float getTotalTimeSpentInSec() {
        return totalTimeSpentInSec;
    }

    public void setTotalTimeSpentInSec(float totalTimeSpentInSec) {
        this.totalTimeSpentInSec = totalTimeSpentInSec;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Supervisor> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }

    public Client getSharingPlatformAdmin() {
        return client;
    }

    public void setSharingPlatformAdmin(Client client) {
        this.client = client;
    }
}
