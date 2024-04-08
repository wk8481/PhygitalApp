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

    public UserDetail saveUser(UserDetail user) {
        return userRepository.save(user);
    }

    public UserDetail getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserDetail> getAllUsers() {
        return userRepository.findAll();
    }
}
