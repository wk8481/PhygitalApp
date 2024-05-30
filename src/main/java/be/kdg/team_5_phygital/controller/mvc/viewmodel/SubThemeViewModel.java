package be.kdg.team_5_phygital.controller.mvc.viewmodel;

public class SubThemeViewModel {
    private int id;
    private String name;
    private String information;
    private int flowId;


    public SubThemeViewModel() {
    }

    public SubThemeViewModel(int id, String name, String information, int flowId) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.flowId = flowId;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }
}
