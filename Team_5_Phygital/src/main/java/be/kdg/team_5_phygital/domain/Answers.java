package be.kdg.team_5_phygital.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "answer")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    private LocalDateTime timestamp;

    @ElementCollection
    private List<String> answer;

    @OneToOne
    private SubTheme subtheme;
}
