//package be.kdg.team_5_phygital.service;
//
//import be.kdg.team_5_phygital.domain.CustomUserDetails;
//import be.kdg.team_5_phygital.domain.User;
//import be.kdg.team_5_phygital.repository.UserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
///**
// * The type Custom user details service.
// */
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    /**
//     * Instantiates a new Custom user details service.
//     *
//     * @param userRepository the users repository
//     */
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//        if (user != null) {
//            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(user.getRole().getCode()));
//            return new CustomUserDetails(
//                    user.getEmail(),
//                    user.getPassword(),
//                    authorities,
//                    user.getId());
//        }
//        throw new UsernameNotFoundException("User " + email + " was not found.");
//    }
//}
