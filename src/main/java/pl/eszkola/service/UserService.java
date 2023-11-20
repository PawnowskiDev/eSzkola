package pl.eszkola.service;

import pl.eszkola.model.User;

public interface UserService {

    void registerUser(User user);

    User getUserByEmail(String email);

    boolean isPasswordValid(String password);

    void giveRating(Long userId, String note);

    void giveNote(Long userId, String note);
}
