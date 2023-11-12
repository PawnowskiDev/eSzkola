package pl.eszkola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;

import javax.swing.*;
import java.util.Optional;

@Service
public abstract class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public abstract void registerUser(User user);
}