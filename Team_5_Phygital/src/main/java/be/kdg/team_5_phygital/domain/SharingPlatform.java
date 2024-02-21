package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
public class SharingPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String contactEmail;

    private int totalParticipants;

    @ManyToOne
    private Administrator administrator;


    public SharingPlatform() {}

    public SharingPlatform(int id, String name, String contactEmail, int totalParticipants) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.totalParticipants = totalParticipants;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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
}
