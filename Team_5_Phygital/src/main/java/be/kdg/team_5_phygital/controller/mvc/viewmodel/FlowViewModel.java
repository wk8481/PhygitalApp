package be.kdg.team_5_phygital.controller.mvc.viewmodel;

public class FlowViewModel {
    private int id;
    private String name;
    private boolean isCircular;

    public FlowViewModel() {
    }

    public FlowViewModel(int id, String name, boolean isCircular) {
        this.id = id;
        this.name = name;
        this.isCircular = isCircular;
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

    public boolean isCircular() {
        return isCircular;
    }

    public void setCircular(boolean circular) {
        isCircular = circular;
    }
}
