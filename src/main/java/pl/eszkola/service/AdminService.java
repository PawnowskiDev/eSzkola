package pl.eszkola.service;

import pl.eszkola.model.User;

public interface AdminService {
    void addUser(User user);

    void deleteUser(Long userId);

    void updateUser(Long userId, User updatedUser);

    void validateEmailFormat(String email);
}
