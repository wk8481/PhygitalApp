package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.UserDetail;
import be.kdg.team_5_phygital.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
