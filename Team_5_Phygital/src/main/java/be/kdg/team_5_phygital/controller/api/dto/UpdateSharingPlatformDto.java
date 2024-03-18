package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateSharingPlatformDto {
    private int id;
    private String name;

    public UpdateSharingPlatformDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UpdateSharingPlatformDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
