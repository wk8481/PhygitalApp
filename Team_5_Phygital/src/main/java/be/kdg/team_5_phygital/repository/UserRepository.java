package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetail, Integer>{
}
