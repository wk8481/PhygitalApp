package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sharing_platform_id")
    private SharingPlatform sharingPlatform;

    public Client() {
    }

    public Client(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Client(String name, String email, String password, SharingPlatform sharingPlatform) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public SharingPlatform getSharingPlatform() {
        return sharingPlatform;
    }

    public void setSharingPlatform(SharingPlatform sharingPlatform) {
        this.sharingPlatform = sharingPlatform;
    }
}
