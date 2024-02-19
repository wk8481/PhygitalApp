package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

@Entity
public class SharingPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sharingPlatformId;

    @ManyToOne
    private Administrator createdBy;
}
