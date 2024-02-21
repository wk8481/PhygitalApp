package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "subtheme")
public class SubTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String information;

}
