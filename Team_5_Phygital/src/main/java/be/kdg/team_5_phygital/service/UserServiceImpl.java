package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.User;
import be.kdg.team_5_phygital.repository.UserRepo;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private UserRepo userRepository;

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
