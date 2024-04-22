package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.User;
import be.kdg.team_5_phygital.domain.UserRole;
import be.kdg.team_5_phygital.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int userId) {
        return userRepository.findById(String.valueOf(userId)).orElse(null);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByMail(String userMail){return userRepository.findByEmail(userMail).orElse(null);}

    @Transactional
    public User saveUser(String email, String password, UserRole userRole) {
        return userRepository.save(new User(email, password, userRole));
    }

    public boolean updateUser(int userId, String email, String password) {
        User user = userRepository.findById(String.valueOf(userId)).orElse(null);
        if (user == null) {
            return false;
        }
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean deleteUser(int userId) {
        Optional<User> user = userRepository.findById(String.valueOf(userId));
        if (user.isEmpty()) {
            return false;
        }
        userRepository.deleteById(String.valueOf(userId));
        return true;
    }
}
