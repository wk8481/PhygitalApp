package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "administrator")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    private String name;

    @OneToMany
    private List<SharingPlatform> hasCreated;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SharingPlatform> getHasCreated() {
        return hasCreated;
    }

    public void setHasCreated(List<SharingPlatform> hasCreated) {
        this.hasCreated = hasCreated;
    }

}
