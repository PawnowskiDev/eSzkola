package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public UserServiceImpl(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isPasswordValid(String password) {
        // Sprawdzenie, czy hasło zawiera co najmniej jedną dużą literę, jedną liczbę, co najmniej jeden znak specjalny i ma co najmniej 8 znaków
        return password.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}");
    }
}
