package be.kdg.team_5_phygital.controller.mvc.viewmodel;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.UserDetail;

import java.util.List;

public class InstallationViewModel {

   private int id;
   private boolean isRunning;

   private UserDetail user;

    private List<Project> projects;



    public InstallationViewModel() {
    }

    public InstallationViewModel(int id, boolean isRunning, UserDetail user, List<Project> projects) {
        this.id = id;
        this.isRunning = isRunning;
        this.user = user;
        this.projects = projects;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
