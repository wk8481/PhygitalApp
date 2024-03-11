package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.UserDetail;

import java.util.List;
import java.util.Optional;


public interface UserService {

    UserDetail createUser(UserDetail user);

    Optional<UserDetail> findUserById(int id);

    List<UserDetail> findAllUsers();
}
