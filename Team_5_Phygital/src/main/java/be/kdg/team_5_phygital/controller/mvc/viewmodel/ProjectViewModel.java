package be.kdg.team_5_phygital.controller.mvc.viewmodel;

public class ProjectViewModel {
    private int id;
    private String name;

    public ProjectViewModel(){

    }

    public ProjectViewModel(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
