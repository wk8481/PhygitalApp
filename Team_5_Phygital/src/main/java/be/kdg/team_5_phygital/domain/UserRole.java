package be.kdg.team_5_phygital.domain;

public enum UserRole {
    ADMIN("ROLE_ADMIN"), MANAGER("ROLE_MANAGER");

    private final String code;

    UserRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
