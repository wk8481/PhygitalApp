package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "subtheme")
public class Subtheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subthemeId;

    private String name;

    private String information;

}
