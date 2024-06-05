package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {
}
