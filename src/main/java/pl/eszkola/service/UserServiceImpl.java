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
    public void registerUser(User user) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
