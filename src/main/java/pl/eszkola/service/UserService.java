package pl.eszkola.service;

import pl.eszkola.model.MyUser;

public interface UserService {

    boolean isPasswordValid(String password);

    void updateUserInfo(Long userId, String address, String phone1, String phone2);

    MyUser getUserByPESEL(String pesel);

    Object getAllUsers();

    void addUser(MyUser myUser);

    MyUser getUserById(Long userId);

    void updateUser(Long userId, MyUser myUser);

    void deleteUser(Long userId);
}
