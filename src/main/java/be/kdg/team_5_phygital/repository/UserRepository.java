package be.kdg.team_5_phygital.repository;



import be.kdg.team_5_phygital.domain.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

//    @Entity()
//    User findUserBy
}

