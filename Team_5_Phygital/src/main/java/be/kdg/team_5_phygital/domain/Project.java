package be.kdg.team_5_phygital.domain;

public class Project {
    private int projectId;

    public Project(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                '}';
    }
    public void createFlow() {
        Flow flow = new Flow();
    }
}
