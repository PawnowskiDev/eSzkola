package pl.eszkola.service;

import pl.eszkola.model.User;

import java.time.LocalDate;

public interface UserService {

    boolean isPasswordValid(String password);

    void updateUserInfo(Long userId, String address, String phone1, String phone2);

    void giveGradeAndNote(Long studentId, Long subjectid, String note, double grade);

    boolean checkAttendance (Long userId, Long classId, LocalDate date);

    User getUserByPESEL(String pesel);

    void excuseAttendance(String pesel);
}
