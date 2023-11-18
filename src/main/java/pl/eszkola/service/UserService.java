package pl.eszkola.service;

import pl.eszkola.model.User;

public interface UserService {

    void registerUser(User user);

    User getUserByEmail(String email);

    boolean isPasswordValid(String password);

}
