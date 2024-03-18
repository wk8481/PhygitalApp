package be.kdg.team_5_phygital.controller.api.dto;

public class UpdateProjectDto {
    private int id;
    private String name;

    public UpdateProjectDto() {
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
        return "UpdateProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
