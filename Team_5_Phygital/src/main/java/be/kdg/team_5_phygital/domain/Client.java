package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sharing_platform_id")
    private SharingPlatform sharingPlatform;

    public Client() {
    }

    public Client(String name, String email, String password) {
        super(name, email, password, UserRole.CLIENT);
    }

    public Client(String name, String email, String password, SharingPlatform sharingPlatform) {
        super(name, email, password, UserRole.CLIENT);
        this.sharingPlatform = sharingPlatform;
    }

    public SharingPlatform getSharingPlatform() {
        return sharingPlatform;
    }

    public void setSharingPlatform(SharingPlatform sharingPlatform) {
        this.sharingPlatform = sharingPlatform;
    }

    @Override
    public String toString() {
        return "Client{" +
                "sharingPlatform=" + sharingPlatform +
                '}';
    }
}
