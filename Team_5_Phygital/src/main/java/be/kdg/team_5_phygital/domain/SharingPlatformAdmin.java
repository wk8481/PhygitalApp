package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
public class SharingPlatformAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

}
