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

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinTable(name = "project")
    private Project project;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subtheme")
    private List<SubTheme> subThemes;

    public Flow() {
    }


}
