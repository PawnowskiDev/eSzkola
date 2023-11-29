package pl.eszkola.service;
import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;

public interface AdminService {
    void addUser(MyUser myUser);

    void deleteUser(Long userId);

    void updateUser(Long userId, MyUser updatedUser);

    void validateEmailFormat(String email);
}
