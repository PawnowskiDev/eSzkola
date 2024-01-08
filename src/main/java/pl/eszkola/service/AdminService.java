package pl.eszkola.service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.SchoolClass;
import pl.eszkola.model.Subject;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    void addUser(MyUser myUser);

    void deleteUser(Long userId);

    void updateUser(Long userId, MyUser updatedUser);

    void validateEmailFormat(String email);

    void addSchoolClass(SchoolClass schoolClass);

    List<MyUser> getUsersByTypeAndKeyword(String userType, String keyword);

    Subject createSubject(String subjectName, String subjectDescription);


    List<Subject> getAllSubjects();

    List<MyUser> getAllTeachers();

    Object getAllUsers();

    void assignUserToSubject(Long userId, Long subjectId);

    Object getAllClass();

    void assignUserToClass(Long user, Long subject);
}

