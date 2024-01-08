package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.Subject;

import java.util.List;
@Service
public interface UserService {

    boolean isPasswordValid(String password);

    void validateEmailFormat(String email);

    void updateUserInfo(Long userId, String address, String phone1, String phone2);

    MyUser getUserByPESEL(String pesel);

    List<MyUser> getAllUsers();

    void saveUser(MyUser myUser);

    MyUser getUserById(Long userId);

    void updateUser(Long userId, MyUser myUser);

    void deleteUser(Long userId);

    void validateUserFields(MyUser myUser);

    List<MyUser> getUsersByTypeAndKeyword(String userType, String keyword);

    MyUser findById(Long userId);

    MyUser findByUsername(String name);

    List<Subject> findByUserType(MyUser user);
}
