package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.User;
import be.kdg.team_5_phygital.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
