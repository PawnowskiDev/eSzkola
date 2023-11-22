package pl.eszkola.service;

import pl.eszkola.model.User;

public interface UserService {

    boolean isPasswordValid(String password);

    void updateUserInfo(Long userId, String address, String phone1, String phone2);

    void addUser(User user);

    void deleteUser(Long userId);

    void updateUser(Long userId, User updatedUser);

    void giveGradeAndNote(Long studentId, Long subjectid, String note, double grade);

    User getUserByPESEL(String pesel);
}
