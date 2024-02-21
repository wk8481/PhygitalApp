package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{
}
