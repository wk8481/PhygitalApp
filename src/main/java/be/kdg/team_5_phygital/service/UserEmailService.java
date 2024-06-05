package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.*;
import be.kdg.team_5_phygital.repository.UserEmailRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserEmailService {
    private static final Logger log = LoggerFactory.getLogger(UserEmailService.class);
    private final UserEmailRepository userEmailRepository;


    public UserEmailService(UserEmailRepository userEmailRepository) {
        this.userEmailRepository = userEmailRepository;
    }

    public UserEmail getUserEmail(int id) {
        return userEmailRepository.findById(id).orElse(null);
    }

    @Transactional
    public UserEmail saveUserEmail(String email, Session session) {
        return userEmailRepository.save(new UserEmail(email, session));
    }
}
