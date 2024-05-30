package be.kdg.team_5_phygital.controller.api.dto;

public class FlowDto {
    private int id;
    private String name;

    public FlowDto() {
    }

    public FlowDto(int id, String name) {
        this.id = id;
        this.name = name;
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
}
