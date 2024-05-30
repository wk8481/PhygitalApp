package be.kdg.team_5_phygital.domain;

public enum UserRole {
    ADMIN("ROLE_ADMIN"), CLIENT("ROLE_CLIENT"), SUPERVISOR("ROLE_SUPERVISOR");

    private final String code;

    UserRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
