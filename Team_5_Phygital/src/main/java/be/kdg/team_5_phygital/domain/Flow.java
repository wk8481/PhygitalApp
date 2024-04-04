package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "flow")
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean isCircular;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SubTheme> subThemes;

    @OneToMany(mappedBy = "flow")
    private List<UserDetail> userDetail;

    public Flow() {
    }

    public Flow(String name) {
        this.name = name;
    }

    public Flow(boolean isCircular, Project project, List<SubTheme> subThemes, List<UserDetail> userDetail) {
        this.isCircular = isCircular;
        this.project = project;
        this.subThemes = subThemes;
        this.userDetail = userDetail;
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

    public List<UserDetail> getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(List<UserDetail> userDetail) {
        this.userDetail = userDetail;
    }


}
