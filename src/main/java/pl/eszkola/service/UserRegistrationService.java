package pl.eszkola.service;

import pl.eszkola.model.User;

public interface UserRegistrationService {
    boolean checkIfUserExist(String email);
    void registerUser (User user);
}
