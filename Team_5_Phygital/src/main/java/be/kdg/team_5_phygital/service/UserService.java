package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.UserDetail;
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


    public UserDetail createUser(UserDetail user) {
        return userRepository.save(user);
    }


    public Optional<UserDetail> findUserById(int id) {
        return userRepository.findById(id);
    }


    public List<UserDetail> findAllUsers() {
        return userRepository.findAll();
    }
}
