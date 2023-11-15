package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registrationUser(User user) {

    }
    @Override
    public User getUserByFullName(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public User getUserByPesel (String email) {
        return userRepository.findByEmail(email);
    }
}
