package pl.eszkola.service;

import pl.eszkola.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    boolean isPasswordValid(String password);

    void updateUserInfo(Long userId, String address, String phone1, String phone2);

    void giveGradeAndNote(Long studentId, Long subjectid, String note, double grade);

    boolean checkAttendance (Long userId, Long classId, LocalDate date);

    User getUserByPESEL(String pesel);

    void excuseAttendance(String pesel);

    Object getAllUsers();

    void addUser(User user);

    User getUserById(Long userId);

    void updateUser(Long userId, User user);

    void deleteUser(Long userId);
}
