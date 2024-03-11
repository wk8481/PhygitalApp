package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "flow")
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isCircular;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SubTheme> subThemes;

    public Flow() {
    }

    public Flow(int id, boolean isCircular, Project project, List<SubTheme> subThemes) {
        this.id = id;
        this.isCircular = isCircular;
        this.project = project;
        this.subThemes = subThemes;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "id=" + id +
                ", isCircular=" + isCircular +
                ", project=" + project +
                ", subThemes=" + subThemes +
                '}';
    }

    public Flow(boolean isCircular, Project project, List<SubTheme> subThemes) {
        this.isCircular = isCircular;
        this.project = project;
        this.subThemes = subThemes;
    }

    public boolean isCircular() {
        return isCircular;
    }

    public void setCircular(boolean circular) {
        isCircular = circular;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<SubTheme> getSubThemes() {
        return subThemes;
    }

    public void setSubThemes(List<SubTheme> subThemes) {
        this.subThemes = subThemes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
