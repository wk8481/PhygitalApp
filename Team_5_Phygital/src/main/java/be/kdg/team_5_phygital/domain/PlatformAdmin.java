package be.kdg.team_5_phygital.domain;

public class PlatformAdmin extends UserRole{

    private String password;
    public PlatformAdmin() {
    }

    public PlatformAdmin(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    public void createSharingPlatform() {
        // TODO - implement PlatformAdmin.createSharingPlatform
        throw new UnsupportedOperationException();
    }

    public void createSharingPlatformAdmin() {
        // TODO - implement PlatformAdmin.createSharingPlatformAdmin
        throw new UnsupportedOperationException();
    }

}
