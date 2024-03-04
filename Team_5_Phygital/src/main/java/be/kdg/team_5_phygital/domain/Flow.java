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
    private Project project;

    @OneToMany()
    @JoinTable(name = "sub_themes")
    private List<SubTheme> subThemes;

    public Flow() {
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
}
