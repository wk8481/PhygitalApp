package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByUserEmail(String email);
}
