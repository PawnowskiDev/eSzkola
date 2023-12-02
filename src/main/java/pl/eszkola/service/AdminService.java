package pl.eszkola.service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.SchoolClass;

import java.util.List;

public interface AdminService {
    void addUser(MyUser myUser);

    void deleteUser(Long userId);

    void updateUser(Long userId, MyUser updatedUser);

    void validateEmailFormat(String email);

    void addSchoolClass(SchoolClass schoolClass);


    List<MyUser> getUsersByTypeAndKeyword(String userType, String keyword);
}

