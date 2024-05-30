package be.kdg.team_5_phygital.controller.mvc.viewmodel;

import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.domain.User;

import java.util.List;

public class InstallationViewModel {

   private int id;
   private boolean isRunning;

   private User user;

    private List<Project> projects;



    public InstallationViewModel() {
    }

    public InstallationViewModel(int id, boolean isRunning, User user, List<Project> projects) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
