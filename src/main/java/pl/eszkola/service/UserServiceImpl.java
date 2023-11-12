package pl.eszkola.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;

import java.util.Optional;
@Service
public class UserServiceImpl extends UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void registerUser(User user) {
        Optional<User> existinguser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (existinguser.isPresent()) {
            throw new RuntimeException("User with this email already exist");
        }

        // kodowanie hasla
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // zapisyawnie
        userRepository.save(user);
    }
}
