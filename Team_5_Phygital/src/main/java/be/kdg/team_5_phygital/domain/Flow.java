package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "flow")
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flowId;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinTable(name = "project")
    private Project project;

    @OneToMany()
    @JoinTable(name = "subtheme")
    private List<Subtheme> listOfSubThemes;
}
