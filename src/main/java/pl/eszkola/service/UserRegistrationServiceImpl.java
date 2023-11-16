package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public UserRegistrationServiceImpl (UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void registerUser(User user) {
        if (checkIfUserExist(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        // kodowanie hasła
        user.setPassword(passwordService.encodePassword(user.getPassword()));
        // zapisywanie użytkownika
        userRepository.save(user);
    }
}

