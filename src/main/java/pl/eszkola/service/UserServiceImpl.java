package pl.eszkola.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Map<Long, MyUser> userMap = new HashMap<>();
    private long nextUserId = 1;

    @Override
    public List<MyUser> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public MyUser getUserById(Long userId) {
        return userMap.get(userId);
    }

    @Override
    public void addUser(MyUser myUser) {
        myUser.setUser_id(nextUserId++);
        // Tutaj możesz dodać logikę do generowania hasła, jeśli jest wymagane
        userMap.put(myUser.getUser_id(), myUser);
    }

    @Override
    public void updateUser(Long userId, MyUser myUser) {
        if (userMap.containsKey(userId)) {
            myUser.setUser_id(userId);
            userMap.put(userId, myUser);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userMap.remove(userId);
    }
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isPasswordValid(String password) {
        // sprawdzamy czy hasło zawiera co najmniej jedną dużą literę, jedną liczbę, co najmniej jeden znak specjalny i ma co najmniej 8 znaków
        return password.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}");
    }

    @Override
    public void updateUserInfo(Long userId, String adress, String phone1, String phone2) {
        // znajdź użytkownika na podstawie ID
        MyUser existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // aktualizuj dane użytkownika
        existingUser.setAdress(adress);
        existingUser.setPhone1(phone1);
        existingUser.setPhone2(phone2);

        // zapisz zaktualizowanego użytkownika w bazie danych
        userRepository.save(existingUser);
    }


    @Override
    public MyUser getUserByPESEL(String pesel) {
        return userRepository.findByPesel(pesel);
    }


}
