package pl.eszkola.service;

import pl.eszkola.model.User;

public interface UserService {

    void registrationUser(User user);
    User getUserByPesel(String pesel);
    User getUserByFullName(String name, String surname);

}
