package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    User createUser(User user);

    Optional<User> findUserById(int id);

    List<User> findAllUsers();
}
